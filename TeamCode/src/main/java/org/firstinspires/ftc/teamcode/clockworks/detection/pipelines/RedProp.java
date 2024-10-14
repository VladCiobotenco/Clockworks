package org.firstinspires.ftc.teamcode.clockworks.detection.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * The processing pipeline for isolating the red prop from the rest.
 */
public final class RedProp extends OpenCvPipeline {

    /**
     * The lower bound of the first lenient filter (any greater value is accepted).
     */
    private static final Scalar LENIENT_LOW_HSV_BOUND = new Scalar(170, 50, 0);

    /**
     * The higher bound of the first lenient filter (any lower value is accepted).
     */
    private static final Scalar LENIENT_HIGH_HSV_BOUND = new Scalar(180, 255, 255);
    /**
     * The average saturation of the mask (after the lenient filter output was recolored).
     */
    private static final int AVG_SATURATION = 255;

    /**
     * The lower bound of the strict filter (any greater value is accepted).
     */
    private static final Scalar STRICT_LOW_HSV_BOUND = new Scalar(90, 0, 0);

    /**
     * The higher bound of the strict filter (any lower value is accepted).
     */
    private static final Scalar STRICT_HIGH_HSV_BOUND = new Scalar(250, 255, 255);

    /**
     * In order to isolate the red prop, the following steps are done:
     * <ul>
     *     <li>The matrix is converted from RGB to HSV (which is more convenient for the following
     *     operations.</li>
     *     <li>A lenient filter is applied to the matrix such that only red props are kept.</li>
     *     <li>The matrix is recolored with the original HSV data.</li>
     *     <li>The saturation of the pixels of the matrix are evened out.</li>
     *     <li>A strict color filter is applied to the matrix.</li>
     *     <li>The matrix is recolored with the original HSV data once again.</li>
     * </ul>
     * @throws RuntimeException the input stream matrix could not be converted from RGB to HSV.
     */
    // TODO: Finish the pipeline.
    // TODO: Fix bug where using any matrix besides input throws an unexpected size exception.
    @Override
    public Mat processFrame(Mat input) throws RuntimeException {
        // Convert from RGB to HSV.
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if (mat.empty()) {
            return input;
        }

        // Get a black and white image of the red pixels.
        Mat thresh = new Mat();
        Core.inRange(mat, LENIENT_LOW_HSV_BOUND, LENIENT_HIGH_HSV_BOUND, thresh);

        // Recolor the image with the values from the HSV matrix.
        Mat masked = new Mat();
        Core.bitwise_and(mat, mat, masked, thresh);

        // Even out the saturation.
        Scalar average = Core.mean(masked, thresh);
        Mat scaledMask = new Mat();
        masked.convertTo(scaledMask, -1, AVG_SATURATION / average.val[1], 0);

        // Apply the stricter filter.
        Mat scaledThresh = new Mat();
        Core.inRange(scaledMask, STRICT_LOW_HSV_BOUND, STRICT_HIGH_HSV_BOUND, scaledThresh);

        // Recolor the matrix once again.
        Mat finalMask = new Mat();
        Core.bitwise_and(mat, mat, finalMask, scaledThresh);

        Mat edges = new Mat();
        Imgproc.Canny(scaledThresh, edges, 100, 200);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(scaledThresh, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        //release all the data
        input.release();
        thresh.copyTo(input);
        scaledThresh.release();
        scaledMask.release();
        mat.release();
        masked.release();
        edges.release();
        thresh.release();
        finalMask.release();

        return input;
    }
}
