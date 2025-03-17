package org.firstinspires.ftc.team28420.BigKostyl;

import com.qualcomm.robotcore.hardware.Servo;

class Claws {
    public Servo servo = null;
    public Claws(Servo servo) {
        this.servo = servo;
    }

    public void take(double compressionForce) {
        // TODO: calibrate
        servo.setPosition(0.5);
    }

    public void leave() {
        servo.setPosition(0);
    }

    public double getCurrentServoPosition() {
        return servo.getPosition();
    }
}
