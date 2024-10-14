package org.firstinspires.ftc.teamcode.clockworks.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * The interface to the launcher mechanism.
 */
public final class Launcher {

    /**
     * The servo position at which the launcher is armed. The value is anything from 0 to 1,
     * representing the ratio between the position that is to be used and the maximum position
     * permitted by the servo.
     */
    private static final double ARM_POSITION = 0.4;

    /**
     * The servo position at which the drone is launched. The value is anything from 0 to 1,
     * representing the ratio between the position that is to be used and the maximum position
     * permitted by the servo.
     */
    private static final double LAUNCH_POSITION = 1;

    /**
     * The name used for the launcher servo in the robot configuration app.
     */
    private static final String HARDWARE_NAME = "Launcher";

    /**
     * The servo used for the launcher mechanism.
     */
    private Servo servo;

    /**
     * Obtains {@link #servo} from the hardware map.
     * @param hardwareMap the hardware map to obtain the motor from.
     */
    public Launcher(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get(HARDWARE_NAME);
    }

    /**
     * Arms the launcher, moving {@link #servo} to {@link #ARM_POSITION}.
     */
    public void arm() {
        servo.setPosition(ARM_POSITION);
    }

    /**
     * Launches the drone, moving {@link #servo} to {@link #LAUNCH_POSITION}.
     */
    public void launch() {
        servo.setPosition(LAUNCH_POSITION);
    }
}
