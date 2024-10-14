package org.firstinspires.ftc.teamcode.clockworks.hardware.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.hardware.Outtake;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * An opmode which tests the outtake mechanism, extending it to its maximum length, then retracts
 * it.
 */
@TeleOp(name = "OuttakeTest", group = "Tests")
public final class OuttakeTest extends OpModeWrapper {

    /**
     * The amount of time to wait between operations, in milliseconds.
     */
    private static final int WAIT_MILLISECONDS = 3000;

    /**
     * The outtake interface that is to be tested.
     */
    private Outtake outtake;

    /**
     * Obtains {@link #outtake}.
     */
    @Override
    protected void onInit() {
        outtake = new Outtake(hardwareMap);
    }

    /**
     * Extends {@link #outtake} to {@link Outtake#MAX_LENGTH}, waits for {@link #WAIT_MILLISECONDS}
     * milliseconds, then retracts it.
     */
    @Override
    protected void onStart() {
        //outtake.setTargetPosition(Outtake.MAX_LENGTH);
        //sleep(WAIT_MILLISECONDS);
        //outtake.flipMin();
        //sleep(WAIT_MILLISECONDS);
        //outtake.flipMax();
        //ssleep(WAIT_MILLISECONDS);
        outtake.retractServo();
        sleep(WAIT_MILLISECONDS);
        outtake.extendServo();
        sleep(WAIT_MILLISECONDS);
        //outtake.retract();
        //sleep(WAIT_MILLISECONDS);
    }
}
