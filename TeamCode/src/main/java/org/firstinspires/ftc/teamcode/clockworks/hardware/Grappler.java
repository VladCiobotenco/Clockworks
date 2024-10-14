package org.firstinspires.ftc.teamcode.clockworks.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * The interface to the grapple mechanism.
 */
public final class Grappler {

    //test ciobo sa vad daca merge
    /**
     * The power at which the motors should be ran when the grapple height is adjusted. The value is
     * anything between 0 and 1, representing the ratio between the power that is to be used and the
     * maximum power of the motor.
     */
    private static final double POWER = 1;

    /**
     * The amount of ticks (as read by the encoder) per inch.
     */
    private static final double TICKS_PER_INCH = 435.61;

    /**
     * The height at which the grapple is fully lifted, in encoder ticks.
     */
    private static final int LIFT_HEIGHT = inchesToTicks(13);


    /**
     * The height at which the grapple is fully down, in encoder ticks.
     */
    private static final int DOWN_HEIGHT = 0;

    /**
     * The name used for the left motor in the robot configuration app.
     */
    private static final String HARDWARE_NAME_LEFT = "GrappleLeft";

    /**
     * The name used for the right motor in the robot configuration app.
     */
    private static final String HARDWARE_NAME_RIGHT = "GrappleRight";

    /**
     * @param inches the amount of inches to convert to encoder ticks
     * @return the amount of encoder ticks which represent the given length
     */
    private static int inchesToTicks(double inches) {
        return (int) (TICKS_PER_INCH * inches);
    }

    /**
     * The left motor of the grapple.
     */
    private final DcMotor motorLeft;

    /**
     * The right motor of the grapple.
     */
    private final DcMotor motorRight;

    private int height;

    /**
     * Obtains {@link #motorLeft} and {@link #motorRight} from the hardware map, makes sure they run
     * using encoders, and reverses the orientation of {@link #motorRight} (such that it moves
     * upwards).
     * @param hardwareMap the hardware map to obtain the two motors from.
     */
    public Grappler(HardwareMap hardwareMap) {
        motorLeft  = hardwareMap.dcMotor.get(HARDWARE_NAME_LEFT);
        motorRight = hardwareMap.dcMotor.get(HARDWARE_NAME_RIGHT);

        motorLeft .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        height = DOWN_HEIGHT;
    }

    /**
     * Moves the grapple to the given height (in encoder ticks), keeping both sides at the same
     * level.
     * @param height the height in ticks.
     */
    private void setTargetPosition(int height) {
        if(height < 0) down();
        if(height > LIFT_HEIGHT) lift();
        else {
            motorLeft .setPower(POWER);
            motorRight.setPower(POWER);
            motorLeft .setTargetPosition(height);
            motorRight.setTargetPosition(height);
            motorLeft .setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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
     * Moves the grapple to {@link #LIFT_HEIGHT}.
     */
    public void lift() {
        setTargetPosition(LIFT_HEIGHT);
    }

    /**
     * Puts the grapple down, to {@link #DOWN_HEIGHT}.
     */
    public void down() {
        setTargetPosition(DOWN_HEIGHT);
    }
}
