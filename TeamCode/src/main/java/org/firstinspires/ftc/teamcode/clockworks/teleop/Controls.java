package org.firstinspires.ftc.teamcode.clockworks.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.clockworks.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.clockworks.hardware.Grappler;
import org.firstinspires.ftc.teamcode.clockworks.hardware.Intake;
import org.firstinspires.ftc.teamcode.clockworks.hardware.Outtake;
import org.firstinspires.ftc.teamcode.clockworks.hardware.Launcher;
import org.firstinspires.ftc.teamcode.clockworks.util.Point2d;

/**
 * The interface to the two gamepads used during the driver period.
 */
public final class Controls {

    /**
     * The speed of {@link #drive}.
     */
    private static final double DRIVE_SPEED = 0.35;

    /**
     * The field orientation of {@link #drive}.
     */
    private final FieldOrientation fieldOrientationController = new FieldOrientation();

    /**
     * The drive of the robot.
     */
    private final SampleMecanumDrive drive;

    /**
     * The intake mechanism.
     */
    private final Intake intake;

    /**
     * The launcher mechanism.
     */
    private final Launcher launcher;

    /**
     * The outtake mechanism.
     */
    private final Outtake outtake;

    /**
     * The grappler mechanism.
     */
    private final Grappler grappler;

    private boolean droneLaunch;

    private boolean intakeThread;
    private boolean outtakeThread;

    /**
     * The trigger mechanisms for {@link #intake} and {@link #intake}.
     */
    private final ToggleTrigger intakeTrigger, outtakeTrigger;

    /**
     * Obtains all fields of the object, and resets their state.
     */
    public Controls(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d());
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fieldOrientationController.setHeading(drive.getRawExternalHeading());
        fieldOrientationController.resetZeroHeading();

        launcher = new Launcher(hardwareMap);
        launcher.arm();

        outtake = new Outtake(hardwareMap);
        outtake.retract();
        outtake.retractServo(); //TODO: make sure this is the right function for retracting the servo
        //TODO: if convenient, add outtake.drop(); or outtake.grip();

        intake = new Intake(hardwareMap);
        intake.retractPosition();

        grappler = new Grappler(hardwareMap);
        grappler.down();

        intakeTrigger = new ToggleTrigger(
                ToggleTrigger.TriggerType.RISING,
                false,
                intake::openPixel,
                intake::stop);

        outtakeTrigger = new ToggleTrigger(
                ToggleTrigger.TriggerType.RISING,
                false,
                outtake::drop,
                outtake::grip);

        droneLaunch = false;

        intakeThread = false;
        outtakeThread = false;
    }

    /**
     * Defines the keybindings for each mechanism, and updates the respective mechanisms.
     */
    public void update(GamepadWrapper gamepad1, GamepadWrapper gamepad2, Telemetry telemetry) {
        intakeTrigger.update(gamepad1.cross);
        outtakeTrigger.update(gamepad2.cross);

        if (gamepad1.dpad_down)
            intake.lowPosition();
        if (gamepad1.dpad_left)
            intake.midPosition();
        if (gamepad1.dpad_right)
            intake.highPosition();
        if (gamepad1.dpad_up && !intakeThread) {
            if(!outtake.isRetracted()) {
                intake.outtakePosition(); //TODO: maybe use intake.retractPosition() instead
                intake.stop();
            }
            else {
                outtake.drop();
                try {
                    new Thread(() -> {
                        try {
                            intakeThread = true;
                            intake.outtakePosition();
                            Thread.sleep(500);
                            intake.openOuttake();
                            Thread.sleep(3000);
                            intake.stop();
                            intake.retractPosition();
                            intakeThread = false;
                        } catch (Exception e) {
                            telemetry.addData("Error inside intake thread: ", e.getMessage());
                            telemetry.update();
                        }
                    }).start();
                } catch (Exception e) {
                    telemetry.addData("Error creating intake thread: ", e.getMessage());
                    telemetry.update();
                }
            }
        }

        if(gamepad1.left_trigger > 0) grappler.decrease(0.75 * gamepad1.left_trigger);
        if(gamepad1.right_trigger  > 0) grappler.increase(0.75 * gamepad1.right_trigger);


        if (gamepad1.triangle)
            droneLaunch = !droneLaunch; //TODO: this will likely be problematic without the use of a TriggerFlipFlop
        if (gamepad2.triangle && droneLaunch)
            launcher.launch();

        //TODO: add other cases for outtake extension and retraction(for lower positions)
        //TODO: separate extend from retract, assign retract to gamepad2.gamepad.dpad_down
        if (gamepad2.dpad_up && !outtakeThread) {
           try {
                new Thread(() -> {
                    try {
                        outtakeThread = true;
                        outtake.grip();
                        outtake.extend();
                        Thread.sleep(500);
                        outtake.extendServo();
                        Thread.sleep(800);
                        outtake.drop();
                        Thread.sleep(800);
                        outtake.grip();
                        Thread.sleep(400);
                        outtake.retractServo();
                        Thread.sleep(800);
                        outtake.retract();
                        outtakeThread = false;
                    } catch (Exception e) {
                        telemetry.addData("Error inside outtake thread: ", e.getMessage());
                        telemetry.update();
                    }
                }).start();
           } catch (Exception e) {
               telemetry.addData("Error creating outtake thread: ", e.getMessage());
               telemetry.update();
           }
        }
        if(gamepad2.left_trigger > 0) outtake.decrease(0.75 * gamepad2.left_trigger); //TODO: don't use until Outtake.TICKS_PER_INCH has correct value
        if(gamepad2.right_trigger > 0) outtake.increase(0.75 * gamepad2.right_trigger); //TODO: don't use until Outtake.TICKS_PER_INCH has correct value

        if (gamepad1.right_bumper)
            fieldOrientationController.resetZeroHeading();

        fieldOrientationController.setHeading(drive.getRawExternalHeading());
        Point2d driveCoords = fieldOrientationController.transformPosition(gamepad1.leftAmplitude() > 0.1
                ? new Point2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x)
                : new Point2d(0, 0));

        drive.setWeightedDrivePower(new Pose2d(
                DRIVE_SPEED * driveCoords.getX(),
                DRIVE_SPEED * driveCoords.getY(),
                -gamepad1.right_stick_x));
        drive.update();

        gamepad1.sendTelemetry(telemetry);
    }
}
