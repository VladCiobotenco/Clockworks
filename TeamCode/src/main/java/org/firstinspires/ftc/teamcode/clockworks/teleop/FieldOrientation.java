package org.firstinspires.ftc.teamcode.clockworks.teleop;

import org.firstinspires.ftc.teamcode.clockworks.util.Point2d;

/**
 * The field orientation of the drive of the robot.
 */
public final class FieldOrientation {

    /**
     * The heading of the robot, in radians.
     */
    private double heading;

    /**
     * The offset to the heading of the robot, in radians.
     */
    private double offset;

    /**
     * Setter for {@link #heading}.
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * Resets the offset of the heading.
     */
    public void resetZeroHeading() {
        offset = heading;
    }

    /**
     * Reverses the perceived orientation of the robot.
     */
    public void reverse() {
        offset += Math.PI;
    }

    public Point2d transformPosition(Point2d point) {
        double angle = heading - offset;
        double x = point.getX() * Math.cos(angle) + point.getY() * Math.sin(angle);
        double y = -point.getX() * Math.sin(angle) + point.getY() * Math.cos(angle);
        return new Point2d(x, y);
    }
}
