package org.firstinspires.ftc.teamcode.clockworks.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * The opmode for the driver period.
 */
@TeleOp(name = "Driver")
public final class Driver extends OpModeWrapper {

    /**
     * The way the two drivers control the robot.
     */
    private Controls controls;

    /**
     * Obtains {@link #controls}.
     */
    @Override
    protected void onInit() {
        controls = new Controls(hardwareMap);
    }

    /**
     * Updates {@link #controls}.
     */
    @Override
    protected void onStart() {
        while (opModeIsActive())
            controls.update((GamepadWrapper) gamepad1, (GamepadWrapper) gamepad2, telemetry);
    }
}
