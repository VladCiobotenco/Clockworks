package org.firstinspires.ftc.teamcode.clockworks.autonomous.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.clockworks.autonomous.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.clockworks.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.clockworks.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.clockworks.autonomous.PlayingField;

/**
 * The autonomous period of the robot, assuming it:
 * <ul>
 *     <li>Starts from F2.</li>
 *     <li>It is placed in the horizontal center of the tile.</li>
 *     <li>It faces left, from the audience's point of view.</li>
 * </ul>
 */
@Autonomous(name = "RedF2")
public final class RedF2 extends AutonomousOpMode {

    // TODO: Add markers for picking up pixels and for dropping them.
    // TODO: Remove discontinuity in the two lines from D1 to D4.
    @Override
    protected TrajectorySequence trajectorySequence() {
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(
                new Pose2d(PlayingField.fromTile(-1.5, -2.552), Math.PI / 2));

        // Goes to the detected team prop, then to the pixel stack on D1.
        switch (propLocation) {
            case DOWN:
                builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(-1.5, -1.25), Math.PI))
                        .lineToConstantHeading(PlayingField.fromTile(-1.5, -0.8))
                        .splineToConstantHeading(PlayingField.fromTile(-2.3, -0.5), -Math.PI);
                break;
            case CENTER:
                builder.lineToConstantHeading(PlayingField.fromTile(-1.5, -1.5))
                        .lineToConstantHeading(PlayingField.fromTile(-1.7, -1.5))
                        .splineToSplineHeading(new Pose2d(PlayingField.fromTile(-2.3, -0.5),
                                Math.PI), Math.PI / 2);
                break;
            case UP:
                builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(-1.5, -1.25), 0))
                        .lineToLinearHeading(new Pose2d(PlayingField.fromTile(-2.3, -0.5), Math.PI));
                break;
        }

        // Goes to the team prop's respective location on the backdrop by moving through column D,
        // until row 2, then going downwards until column E.
        builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(-1.25, -0.5), Math.PI / 2))
                .lineToConstantHeading(PlayingField.fromTile(1, -0.5));
        switch (propLocation) {
            case DOWN:
                builder.splineToSplineHeading(new Pose2d(PlayingField.fromTile(1.975, -1.25), 0),
                        -Math.PI / 2);
                break;
            case CENTER:
                builder.splineToSplineHeading(new Pose2d(PlayingField.fromTile(1.975, -1.5), 0),
                        -Math.PI / 2);
                break;
            case UP:
                builder.splineToSplineHeading(new Pose2d(PlayingField.fromTile(1.975, -1.75), 0),
                        -Math.PI / 2);
                break;
        }

        // Goes to the pixel stack on square D1, by going down from the backdrop to column F, moving
        // until row 1, then going up until column D.
        builder.lineToConstantHeading(PlayingField.fromTile(1.95, -2))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(0.5, -2.4), -Math.PI / 2),
                        Math.PI)
                .lineToConstantHeading(PlayingField.fromTile(-1, -2.4))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(-2.36, -1.5), -Math.PI),
                        Math.PI * 0.5)
                .lineToConstantHeading(PlayingField.fromTile(-2.36, -0.5));

        // Goes to the backdrop by moving through column D, until row 2, then going downwards until
        // column E.
        builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(-1.25, -0.5), Math.PI / 2))
                .lineToConstantHeading(PlayingField.fromTile(1, -0.5))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(1.975, -1.25), 0),
                        -Math.PI / 2);

        return builder.build();
    }
}
