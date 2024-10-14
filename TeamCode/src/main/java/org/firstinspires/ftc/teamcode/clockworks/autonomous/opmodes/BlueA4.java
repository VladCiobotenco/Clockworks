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
 *     <li>Starts from A4.</li>
 *     <li>It is placed in the horizontal center of the tile.</li>
 *     <li>It faces right, from the audience's point of view.</li>
 * </ul>
 */
@Autonomous(name = "BlueA4")
public final class BlueA4 extends AutonomousOpMode {

    // TODO: Add markers for picking up pixels and for dropping them.
    // TODO: Remove discontinuity in the two lines from C1 to C4.
    @Override
    protected TrajectorySequence trajectorySequence() {
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(
                new Pose2d(PlayingField.fromTile(0.5, 2.552), -Math.PI / 2));

        // Goes to the detected team prop, then to its respective location in the backdrop.
        switch (propLocation) {
            case DOWN:
                builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(0.5, 1.25), Math.PI))
                        .lineToLinearHeading(new Pose2d(PlayingField.fromTile(2, 1.25), 0));
                break;
            case CENTER:
                builder.lineToConstantHeading(PlayingField.fromTile(0.5, 1.5))
                        .lineToLinearHeading(new Pose2d(PlayingField.fromTile(2, 1.5), 0));
                break;
            case UP:
                builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(0.5, 1.25), 0))
                        .lineToConstantHeading(PlayingField.fromTile(0.525, 1.75))
                        .splineToConstantHeading(PlayingField.fromTile(2, 1.75), 0);
                break;
        }

        // Goes to the pixel stack on square C1, by going down from the backdrop to column A, moving
        // until row 1, then going up until column C.
        builder.lineToConstantHeading(PlayingField.fromTile(1.95, 2))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(0.5, 2.4), Math.PI / 2),
                        -Math.PI)
                .lineToConstantHeading(PlayingField.fromTile(-1, 2.4))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(-2.36, 1.5), Math.PI),
                        -Math.PI * 0.5)
                .lineToConstantHeading(PlayingField.fromTile(-2.36, 0.5));

        // Goes to the backdrop by moving through column C, until row 2, then going downwards until
        // column B.
        builder.lineToLinearHeading(new Pose2d(PlayingField.fromTile(-1.25, 0.5), -Math.PI / 2))
                .lineToConstantHeading(PlayingField.fromTile(1, 0.5))
                .splineToSplineHeading(new Pose2d(PlayingField.fromTile(1.975, 1.25), 0),
                        Math.PI / 2);

        return builder.build();
    }
}
