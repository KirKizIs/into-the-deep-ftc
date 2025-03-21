package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.util.Vars;

class Claws {

    public Servo servo;

    public Claws(Servo servo) {
        this.servo = servo;
    }

    public void take() {
        servo.setPosition(Vars.Grabber.Claws.TAKE_POSITION);
    }

    public void leave() {
        servo.setPosition(Vars.Grabber.Claws.LEAVE_POSITION);
    }

}