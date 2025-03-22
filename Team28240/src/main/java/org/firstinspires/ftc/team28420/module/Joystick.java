package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.types.Pos;

public class Joystick {

    public Gamepad gamepad;

    public Joystick(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public Pos getLeftStickPos() {
        return new Pos(
            Math.abs(gamepad.left_stick_x)>0.2?gamepad.left_stick_x*0.8:0,
            Math.abs(gamepad.left_stick_y)>0.2?-gamepad.left_stick_y*0.8:0
        );
    }

    public Pos getRightStickPos() {
        return new Pos(
                Math.abs(gamepad.right_stick_x)>0.2?gamepad.right_stick_x:0,
                Math.abs(gamepad.right_stick_y)>0.2?-gamepad.right_stick_y:0
        );
    }

}
