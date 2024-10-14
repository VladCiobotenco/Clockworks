package org.firstinspires.ftc.teamcode.clockworks.hardware.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.hardware.Launcher;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * An opmode which tests the launcher mechanism, arming it and then launching the drone.
 */
@TeleOp(name = "LauncherTest", group = "Tests")
public final class LauncherTest extends OpModeWrapper {

    /**
     * The amount of time to wait between operations, in milliseconds.
     */
    private static final int WAIT_MILLISECONDS = 10000;

    /**
     * The launcher interface that is to be tested.
     */
    private Launcher launcher;

    /**
     * Obtains {@link #launcher}.
     */
    @Override
    protected void onInit() {
        launcher = new Launcher(hardwareMap);
    }

    /**
     * Arms {@link #launcher}, waits for {@link #WAIT_MILLISECONDS} milliseconds, then launches the
     * drone.
     */
    @Override
    protected void onStart() {
        launcher.arm();
        sleep(WAIT_MILLISECONDS);
        launcher.launch();
        sleep(WAIT_MILLISECONDS);
    }
}
