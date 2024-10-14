package org.firstinspires.ftc.teamcode.clockworks.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * The interface to the intake mechanism.
 */
public final class Intake {

    /**
     * The power at which the motor should be ran when the intake mechanism is activated. The value
     * is anything between 0 and 1, representing the ratio between the power that is to be used and
     * the maximum power of the motor.
     */
    private static final double OPEN_POWER_PIXEL = 0.62;
    private static final double OPEN_POWER_OUTTAKE = 0.85;


    /**
     * The power at which the motor should be ran when the intake mechanism is stopped. The value
     * is anything between 0 and 1, representing the ratio between the power that is to be used and
     * the maximum power of the motor.
     */
    private static final double STOP_POWER = 0;

    private static final double POS_LOW = 0.122;
    private static final double POS_MID = 0.151;
    private static final double POS_HIGH = 0.195;
    private static final double POS_OUTTAKE = 0.46;
    private static final double POS_RETRACT = 0.5;

    /**
     * The name used for the intake motor in the robot configuration app.
     */
    private static final String HARDWARE_NAME_MOTOR = "IntakeMotor";

    /**
     * The name used for the intake servo in the robot configuration app.
     */
    private static final String HARDWARE_NAME_SERVO = "IntakeServo";

    /**
     * The motor used for the intake mechanism.
     */
    private final DcMotor motor;

    /**
     * The servo used for the intake mechanism (for flipping it).
     */
    private final Servo servo;

    /**
     * Obtains {@link #motor} and {@link #servo} from the hardware map.
     * Reverses the orientation of {@link #motor}.
     * @param hardwareMap the hardware map to obtain the motor and servo from.
     */
    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(HARDWARE_NAME_MOTOR);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        servo = hardwareMap.servo.get(HARDWARE_NAME_SERVO);
    }

    /**
     * Activates the intake mechanism, setting {@link #motor}'s power to {@link #OPEN_POWER_PIXEL}.
     */
    public void openPixel() {
        motor.setPower(OPEN_POWER_PIXEL);
    }

    public void openOuttake() {
        motor.setPower(OPEN_POWER_OUTTAKE);
    }


    /**
     * Stops the intake mechanism, setting {@link #motor}'s power to {@link #STOP_POWER}.
     */
    public void stop() {
        motor.setPower(STOP_POWER);
    }

    /**
     * Flips the intake mechanism (using the servo), up to the given position.
     */
    private void rotate(double position) {
        servo.setPosition(position);
    }

    public void lowPosition() {
        rotate(POS_LOW);
    }

    public void midPosition() {
        rotate(POS_MID);
    }

    public void highPosition() {
        rotate(POS_HIGH);
    }

    public void retractPosition() {
        rotate(POS_RETRACT);
    }

    public void outtakePosition() {
        rotate(POS_OUTTAKE);
    }

}
