package org.firstinspires.ftc.teamcode.clockworks.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.clockworks.util.MathUtil;

/**
 * A convenient wrapper for {@link Gamepad}.
 */
public final class GamepadWrapper extends Gamepad{

    public GamepadWrapper() {
        super();
    }

    /**
     * Writes the current status to the given telemetry.
     * Does nothing if the given telemetry is null.
     * @param telemetry the telemetry to write to.
     */
    public void sendTelemetry(Telemetry telemetry) {
        if (telemetry == null)
            return;

        telemetry.addData("leftAngle: "     , leftAngle());
        telemetry.addData("rightAngle: "    , rightAngle());
        telemetry.addData("leftAmplitude: " , leftAmplitude());
        telemetry.addData("rightAmplitude: ", rightAmplitude());

        telemetry.update();
    }

    /**
     * @return the angle of the left joystick (see {@link MathUtil#angle}).
     */
    public double leftAngle() {
        return MathUtil.angle(left_stick_x, left_stick_y);
    }

    /**
     * @return the angle of the right joystick (see {@link MathUtil#angle}).
     */
    public double rightAngle() {
        return MathUtil.angle(right_stick_x, right_stick_y);
    }

    /**
     * @return the amplitude of the left joystick (see {@link MathUtil#absoluteValue}).
     */
    public double leftAmplitude() {
        return MathUtil.absoluteValue(left_stick_x, left_stick_y);
    }

    /**
     * @return the amplitude of the right joystick (see {@link MathUtil#absoluteValue}).
     */
    public double rightAmplitude() {
        return MathUtil.absoluteValue(left_stick_x, left_stick_y);
    }
}
