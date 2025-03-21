package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.util.Vars;

class Wrist {

    public Servo servo;

    public Wrist(Servo servo) {
        this.servo = servo;
    }

    public void reset() {
        servo.setPosition(Vars.Grabber.Wrist.START_POSITION);
    }

    public void turnToTop() {
        servo.setPosition(Vars.Grabber.Wrist.TOP_POSITION);
    }

    public void turnToStraight() {
        servo.setPosition(Vars.Grabber.Wrist.STRAIGHT_POSITION);
    }

    public void turnToBottom() {
        servo.setPosition(Vars.Grabber.Wrist.BOTTOM_POSITION);
    }

}