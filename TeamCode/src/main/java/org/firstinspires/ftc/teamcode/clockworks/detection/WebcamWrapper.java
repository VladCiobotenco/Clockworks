package org.firstinspires.ftc.teamcode.clockworks.detection;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

/**
 * A convenient wrapper for {@link OpenCvWebcam}.
 */
public final class WebcamWrapper {

    /**
     * The X resolution (in pixels) of {@link #webcam}.
     */
    private static final int WEBCAM_WIDTH = 800;

    /**
     * The Y resolution (in pixels) of {@link #webcam}.
     */
    private static final int WEBCAM_HEIGHT = 448;

    /**
     * The maximum amount of frames per second of {@link #webcam}.
     */
    private static final int MAX_FPS = 5;

    /**
     * The webcam to stream from.
     */
    private OpenCvWebcam webcam;

    /**
     * Obtains {@link #webcam}.
     * @param hardwareMap the hardware map to obtain {@link #webcam} from.
     * @param hardwareName the name of the camera in the hardware map.
     */
    public WebcamWrapper(HardwareMap hardwareMap, String hardwareName) {
        int cameraId = hardwareMap.appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id",
                        hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory
                .getInstance()
                .createWebcam(hardwareMap.get(WebcamName.class, hardwareName), cameraId);
    }

    /**
     * Opens {@link #webcam}, such that it starts streaming.
     */
    public void open() {
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                webcam.startStreaming(WEBCAM_WIDTH, WEBCAM_HEIGHT, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(webcam, MAX_FPS);
            }

            @Override
            public void onError(int errorCode) {}
        });
    }

    /**
     * Closes {@link #webcam}, such that it stops streaming.
     */
    public void close() {
        webcam.stopStreaming();
    }

    /**
     * Sets the processing pipeline of {@link #webcam}.
     */
    public void setPipeline(PipelineType pipelineType) {
        webcam.setPipeline(pipelineType.pipeline);
    }
}
