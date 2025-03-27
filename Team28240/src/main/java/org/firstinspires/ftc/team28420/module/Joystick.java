package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.types.Pos;

public class Joystick {

    public Gamepad gamepad;

    public Joystick(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public boolean getLeftBumper() {
        return gamepad.left_bumper;
    }

    public boolean getRightBumper() {
        return gamepad.right_bumper;
    }

    public boolean getDPadUp() {
        return gamepad.dpad_up;
    }

    public boolean getDpadDown() {
        return gamepad.dpad_down;
    }

    public boolean getDPadLeft() {
        return gamepad.dpad_left;
    }

    public boolean getDPadRight() {
        return gamepad.dpad_right;
    }

    public boolean getTriangle() {
        return gamepad.triangle;
    }

    public boolean getCircle() {
        return gamepad.circle;
    }

    public boolean getCross() {
        return gamepad.cross;
    }

    public boolean getSquare() {
        return gamepad.square;
    }

    public double getLeftTrigger() {
        float x = gamepad.left_trigger;
        return x*x;
    }
    public double getRightTrigger() {
        float x = gamepad.right_trigger;
        return x*x;
    }

    public Pos getLeftStickPos() {
        float x = gamepad.left_stick_x, y = gamepad.left_stick_y;

        x = ( x > 0 ? x*x : -x*x );
        y = ( y > 0 ? y*y : -y*y );

        return new Pos(
            Math.abs(x) > 0.1 ? x * 0.5 : 0,
            Math.abs(y) > 0.1 ? -y * 0.5 : 0
        );
    }

    public Pos getRightStickPos() {
        float x = gamepad.right_stick_x, y = gamepad.right_stick_y;

        x = ( x > 0 ? x*x : -x*x );
        y = ( y > 0 ? y*y : -y*y );

        return new Pos(
                Math.abs(x) > 0.1 ? x * 0.2 : 0,
                Math.abs(y) > 0.1 ? -y * 0.2 : 0
        );
    }

}
