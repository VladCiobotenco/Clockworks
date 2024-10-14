package org.firstinspires.ftc.teamcode.clockworks.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * The interface to the outtake mechanism.
 */
public final class Outtake {

    /**
     * The power at which the motor should be run when the outtake mechanism moves. The value
     * is anything between 0 and 1, representing the ratio between the power that is to be used and
     * the maximum power of the motor.
     */
    private static final double POWER = 1;

    /**
     * The amount of ticks (as read by the encoder) per inch.
     */
    // TODO: Find out the real value.
    private static final double TICKS_PER_INCH = 422; // should be less than 105

    /**
     * The maximum length the outtake can extend to, in encoder ticks.
     */
    // TODO: Find out the real value. (Measure real max height with a ruler)
    // TODO: It it's currently public only for testing purposes, otherwise it **could** be priate.
    public static final int MAX_HEIGHT = inchesToTicks(2); //844 ticks, should be around 20 inch I think

    private int height;

    /**
     * The height at which the outtake is fully retracted, in encoder ticks.
     */
    private static final int RETRACT_HEIGHT = 0;

    private static final double GRIPPER_MIN = 0.935;
    private static final double GRIPPER_MAX = 0.77;

    private static final double FLIP_MIN = 0.805;
    private static final double FLIP_MAX = 0.25;

    /**
     * The name used for the outtake motor in the robot configuration app.
     */
    private static final String HARDWARE_NAME_MOTOR = "OuttakeMotor";

    private static final String HARDWARE_NAME_SERVO_FLIP = "OuttakeServoFlip";
    private static final String HARDWARE_NAME_SERVO_GRIPPER = "OuttakeServoGripper";

    /**
     * @param inches the amount of inches to convert to encoder ticks
     * @return the amount of encoder ticks which represent the given length
     */
    private static int inchesToTicks(double inches) {
        return (int) (TICKS_PER_INCH * inches);
    }

    /**
     * The motor used for the outtake mechanism.
     */
    private final DcMotor motor;

    private final Servo servoFlip;
    private final Servo servoGripper;

    /**
     * Obtains {@link #motor} from the hardware map, and makes sure it runs using encoders.
     * @param hardwareMap the hardware map to obtain the motor from.
     */
    // TODO: See if direction needs to be reversed.
    public Outtake(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(HARDWARE_NAME_MOTOR);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        servoFlip = hardwareMap.servo.get(HARDWARE_NAME_SERVO_FLIP);
        servoGripper = hardwareMap.servo.get(HARDWARE_NAME_SERVO_GRIPPER);

        height = RETRACT_HEIGHT;
    }

    /**
     * Extends the outtake to the given length (in encoder ticks).
     * @param height the height in ticks.
     */
    // TODO: It is currently public only for testing purposes, otherwise it **could** be private.
    public void setTargetPosition(int height) {
        if (height < 0) retract();
        else if (height > MAX_HEIGHT) extend();
        else {
            motor.setPower(POWER);
            motor.setTargetPosition(height);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            this.height = height;
        }
    }

    public void increase(double increment) {
        setTargetPosition(height + inchesToTicks(increment));
    }

    public void decrease(double decrement) {
        setTargetPosition(height - inchesToTicks(decrement));
    }

    /**
     * Extends the outtake length, to {@link #MAX_HEIGHT}.
     */
    public void extend() {
        setTargetPosition(MAX_HEIGHT);
    }

    /**
     * Decreases the outtake length, to {@link #RETRACT_HEIGHT}.
     */
    public void retract() {
        setTargetPosition(RETRACT_HEIGHT);
    }


    public void retractServo() { //TODO: make sure the name isn't mixed up with extendServo()
        servoFlip.setPosition(FLIP_MIN);
    }

    public void extendServo() {
        servoFlip.setPosition(FLIP_MAX);
    }

    public boolean isRetracted() {
        return height == RETRACT_HEIGHT && servoFlip.getPosition() == FLIP_MIN;
    }

    public void grip() {
        servoGripper.setPosition(GRIPPER_MIN);
    }

    public void drop() {
        servoGripper.setPosition(GRIPPER_MAX);
    }
}
