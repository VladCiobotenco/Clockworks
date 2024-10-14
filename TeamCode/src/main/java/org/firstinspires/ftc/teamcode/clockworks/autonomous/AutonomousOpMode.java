package org.firstinspires.ftc.teamcode.clockworks.autonomous;

import org.firstinspires.ftc.teamcode.clockworks.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.clockworks.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.clockworks.util.OpModeWrapper;

/**
 * A type of opmode meant to be used for autonomous opmodes.
 */
public abstract class AutonomousOpMode extends OpModeWrapper {

    /**
     * The mecanum drive of the robot.
     */
    protected SampleMecanumDrive drive;

    /**
     * The location where the prop had been detected to be.
     */
    // TODO: Make it no longer have a random value once prop detection is implemented.
    protected PlayingField.PropLocation propLocation = PlayingField.PropLocation.DOWN;

    /**
     * The {@link TrajectorySequence} that the robot will follow.
     */
    private TrajectorySequence trajectorySequence;

    /**
     * Generates the value to be used for {@link #trajectorySequence}.
     */
    protected abstract TrajectorySequence trajectorySequence();

    /**
     * Generates {@link #trajectorySequence}.
     */
    @Override
    protected final void onInit() {
        trajectorySequence = trajectorySequence();
    }

    /**
     * Makes the robot follow {@link #trajectorySequence}.
     */
    @Override
    protected final void onStart() {
        drive.followTrajectorySequence(trajectorySequence);
    }
}
