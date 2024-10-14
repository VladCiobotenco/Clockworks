package org.firstinspires.ftc.teamcode.clockworks.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.clockworks.detection.WebcamWrapper;

/**
 * An interface to the two webcams of the robot.
 */
public final class Camera {

    /**
     * The name used for the front webcam in the robot configuration app.
     */
    private static final String HARDWARE_NAME_FRONT = "WebcamFront";

    /**
     * The name used for the rear webcam in the robot configuration app.
     */
    private static final String HARDWARE_NAME_REAR = "WebcamRear";

    /**
     * The webcam on the front.
     */
    // TODO: Make private once we are done with testing.
    public WebcamWrapper webcamFront;

    /**
     * The webcam on the rear.
     */
    private WebcamWrapper webcamRear;

    /**
     * Obtains {@link #webcamFront}.
     */
    // TODO: Obtain the rear webcam as well.
    public Camera(HardwareMap hardwareMap) {
        webcamFront = new WebcamWrapper(hardwareMap, HARDWARE_NAME_FRONT);
    }
}
