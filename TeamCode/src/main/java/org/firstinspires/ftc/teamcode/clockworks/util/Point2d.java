package org.firstinspires.ftc.teamcode.clockworks.util;

import java.util.Objects;

public final class Point2d {
    private final double x;
    private final double y;

    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2d point = (Point2d) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
