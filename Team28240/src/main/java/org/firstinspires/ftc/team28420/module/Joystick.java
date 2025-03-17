package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.Vars;

public class Joystick {

    public Gamepad gamepad;

    public Joystick(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public static Vars.Vector getVectorFromPos(Vars.Pos pos) {
        return new Vars.Vector(Math.atan2(pos.y, pos.x), Math.hypot(pos.x, pos.y));
    }

    public Vars.Pos getLeftStickPos() {
        return new Vars.Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public Vars.Pos getRightStickPos() {
        return new Vars.Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

}
