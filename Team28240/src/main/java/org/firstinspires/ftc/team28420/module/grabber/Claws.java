package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.util.Vars;

public class Claws {

    public final Servo servo;

    public Claws(Servo servo) {
        this.servo = servo;
        this.servo.scaleRange(Vars.Grabber.Claws.TAKE_POSITION, Vars.Grabber.Claws.LEAVE_POSITION);
    }

    public void setPosition(double position) {
        servo.setPosition(position);
    }

    public void take() {
        servo.setPosition(Vars.Grabber.Claws.TAKE_POSITION);
    }

    public void leave() {
        servo.setPosition(Vars.Grabber.Claws.LEAVE_POSITION);
    }

}