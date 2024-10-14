package org.firstinspires.ftc.teamcode.clockworks.util;

/**
 * Various utilities for the underlying mathematics of the robot.
 */
public abstract class MathUtil {

    /**
     * @return the angle of the given cartesian coordinates, with the following remarks:
     * <ul>
     *     <li>Angle 0 is facing on +y, not on +x.</li>
     *     <li>The angle is in degrees, not radians.</li>
     *     <li>The angle is modulo 360 degrees. Intuitively, rotating a full circle is equivalent to
     *     rotating 0 degrees (with the consequence that a full rotation plus "something" is equal
     *     just to that "something"). </li>
     *     <li>Any angle strictly less than 180 is positive, any angle strictly more than 180 is
     *     negative. Intuitively, the signed angle represents the shortest rotation from angle 0 to
     *     get to said angle.</li>
     * </ul>
     */
    public static double angle(double x, double y) {

        // Obtain the angle (in radians) of the position.
        double angle = Math.atan2(-y, x);

        // Make angle 0 at +y, not at +x (see 1st point in javadoc).
        angle -= Math.PI / 2;

        // Convert the angle to degrees (see 2nd point in javadoc).
        angle *= 180 / Math.PI;

        // Ignore full rotations (see 3rd point in javadoc).
        angle %= 360;

        // Sign the angle (see 4th point in javadoc).
        if (angle > 180) angle -= 360;
        else if (angle < -180) angle +=360;

        return angle;
    }

    /**
     * @return the absolute value of the given point (the distance from the origin).
     */
    public static double absoluteValue(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
