package org.firstinspires.ftc.teamcode.clockworks.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * A convenient wrapper for {@link com.qualcomm.robotcore.eventloop.opmode.LinearOpMode}.
 */
public abstract class OpModeWrapper extends LinearOpMode {

    /**
     * Gets called when the init button is pressed, and thus should initialise the state required
     * for running the opmode.
     */
    protected abstract void onInit();

    /**
     * Gets called when the start button is pressed, and stops when the stop button is pressed.
     */
    protected abstract void onStart();

    /**
     * Calls {@link #onInit} and runs the opmode only if {@link #isStopRequested} is false.
     * @throws InterruptedException when the opmode is stopped in the middle of execution
     */
    @Override
    public final void runOpMode() throws InterruptedException {
        onInit();
        waitForStart();
        onStart();
    }
}
