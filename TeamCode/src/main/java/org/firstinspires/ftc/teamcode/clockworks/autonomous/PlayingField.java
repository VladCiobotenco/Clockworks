package org.firstinspires.ftc.teamcode.clockworks.autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;

/**
 * Various constants and utility functions for the playing field.
 */
public abstract class PlayingField {

    /**
     * The prop location, from the audience's point of view.
     */
    //test
    public enum PropLocation {
        DOWN,
        CENTER,
        UP
    }

    /**
     * The length of a square tile, in inches.
     */
    public static final double TILE_SIZE = 24.01;

    /**
     * @return a {@link Vector2d} whose coordinates are multiples of {@link #TILE_SIZE}.
     */
    public static Vector2d fromTile(double x, double y) {
        return new Vector2d(x * TILE_SIZE, y * TILE_SIZE);
    }
}
