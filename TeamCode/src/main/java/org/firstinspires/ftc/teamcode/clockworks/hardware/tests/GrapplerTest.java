package org.firstinspires.ftc.teamcode.clockworks.hardware.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.hardware.Grappler;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * An opmode which tests the grapple mechanism, lifting it up, then putting it down.
 */
@TeleOp(name = "GrapplerTest", group = "Tests")
public final class GrapplerTest extends OpModeWrapper {

    /**
     * The amount of time to wait between operations, in milliseconds.
     */
    private static final int WAIT_MILLISECONDS = 10000;

    /**
     * The grapple interface that is to be tested.
     */
    private Grappler grapple;

    /**
     * Obtains {@link #grapple}.
     */
    @Override
    protected void onInit() {
        grapple = new Grappler(hardwareMap);
    }

    /**
     * Lifts {@link #grapple} to its maximum position, waits for {@link #WAIT_MILLISECONDS}
     * milliseconds, then puts it down.
     */
    @Override
    protected void onStart() {
        grapple.lift();
        sleep(WAIT_MILLISECONDS);
        grapple.down();
        sleep(WAIT_MILLISECONDS);
    }
}