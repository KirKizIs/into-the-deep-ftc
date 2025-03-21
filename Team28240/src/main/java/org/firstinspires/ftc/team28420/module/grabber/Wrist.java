package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.util.Vars;

public class Wrist {

    public final Servo servo;

    public Wrist(Servo servo) {
        this.servo = servo;
        this.servo.scaleRange(Vars.Grabber.Wrist.TOP_POSITION, Vars.Grabber.Wrist.BOTTOM_POSITION);
    }

    public void setPosition(double position) {
        servo.setPosition(position);
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