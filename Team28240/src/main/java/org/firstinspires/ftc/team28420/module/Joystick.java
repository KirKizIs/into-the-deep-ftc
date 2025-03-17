package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.Vars;

public class Joystick {

    public Gamepad gamepad;

    public Joystick(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public Vars.Pos getLeftStickPos() {
        return new Vars.Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public Vars.Pos getRightStickPos() {
        return new Vars.Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

}
