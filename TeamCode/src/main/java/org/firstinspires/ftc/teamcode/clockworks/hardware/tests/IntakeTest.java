package org.firstinspires.ftc.teamcode.clockworks.hardware.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.hardware.Intake;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * An opmode which tests the intake mechanism, making it run for a given amount of time, stopping
 * it, then making it go to a given position (and returning it).
 */
@TeleOp(name = "IntakeTest", group = "Tests")
public final class IntakeTest extends OpModeWrapper {

    /**
     * The amount of time to wait between operations, in milliseconds.
     */
    private static final int WAIT_MILLISECONDS = 2000;

    /**
     * The intake interface that is to be tested.
     */
    private Intake intake;

    /**
     * Obtains {@link #intake}.
     */
    @Override
    protected void onInit() {
        intake = new Intake(hardwareMap);
    }

    /**
     * Runs {@link #intake} for {@link #WAIT_MILLISECONDS} milliseconds, then stops it.
     */
    @Override
    protected void onStart() {
        intake.retractPosition();
        sleep(WAIT_MILLISECONDS);
        intake.lowPosition();
        sleep(WAIT_MILLISECONDS);
        intake.openPixel();
        sleep(WAIT_MILLISECONDS);
        intake.stop();
        sleep(WAIT_MILLISECONDS);
        intake.outtakePosition();
        sleep(WAIT_MILLISECONDS);
        intake.openOuttake();
        sleep(WAIT_MILLISECONDS + 1000);
        intake.stop();
        sleep(WAIT_MILLISECONDS);
    }
}
