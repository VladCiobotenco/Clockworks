package org.firstinspires.ftc.teamcode.clockworks.detection;

import org.firstinspires.ftc.teamcode.clockworks.detection.pipelines.RedProp;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * The processing pipeline that is to be used (see the
 * {@link org.firstinspires.ftc.teamcode.clockworks.detection.pipelines pipelines} package).
 */
public enum PipelineType {
    RED_PROP(new RedProp());

    /**
     * The actual pipeline represented by the enum.
     */
    public final OpenCvPipeline pipeline;

    /**
     * Obtains {@link #pipeline}.
     */
    PipelineType(OpenCvPipeline pipeline) {
        this.pipeline = pipeline;
    }
}
