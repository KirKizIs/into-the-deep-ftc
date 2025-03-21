package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.types.Pos;

public class Joystick {

    public Gamepad gamepad;

    public Joystick(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public Pos getLeftStickPos() {
        return new Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public Pos getRightStickPos() {
        return new Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

}
