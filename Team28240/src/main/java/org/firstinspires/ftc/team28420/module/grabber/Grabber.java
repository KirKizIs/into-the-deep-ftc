package org.firstinspires.ftc.team28420.module.grabber;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber {

    public final Intake intake;
    public final Wrist wrist;
    public final Claws claws;

    public Grabber(DcMotor beltMotor, Servo wristServo, Servo clawsServo) {
        intake = new Intake(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
    }

    public void setup() {
        intake.toDefaultPos();
        wrist.turnToBottom();
        claws.take();
    }

    @NonNull
    @Override
    public String toString() {
        return "Belt motor position: " + intake.getCurrentPosition() +
                " Wrist servo position: " + wrist.servo.getPosition() +
                " Claws servo position: " + claws.servo.getPosition();
    }
}