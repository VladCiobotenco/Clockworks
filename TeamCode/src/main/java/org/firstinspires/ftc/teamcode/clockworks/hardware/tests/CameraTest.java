package org.firstinspires.ftc.teamcode.clockworks.hardware.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.clockworks.detection.PipelineType;
import org.firstinspires.ftc.teamcode.clockworks.hardware.Camera;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * An opmode which tests the camera system.
 */
// TODO: Implement behaviour for the rear webcam as well.
@TeleOp(name = "CameraTest", group = "Tests")
public final class CameraTest extends OpModeWrapper {

    /**
     * The processing pipeline to be applied to the camera stream (see {@link PipelineType}).
     */
    public static PipelineType pipelineType = PipelineType.RED_PROP;

    /**
     * The camera system which is to be tested.
     */
    private Camera camera;

    /**
     * Obtains {@link #camera}, sets the front webcam's pipeline to {@link #pipelineType}, and opens
     * said webcam.
     */
    @Override
    protected void onInit() {
        camera = new Camera(hardwareMap);
        camera.webcamFront.setPipeline(pipelineType);
        camera.webcamFront.open();
    }

    // Does nothing yet.
    // TODO: Figure out why opening camera inside this function doesn't work.
    @Override
    protected void onStart() {}
}
