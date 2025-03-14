package org.firstinspires.ftc.team28420.BigKostyl;

import com.qualcomm.robotcore.hardware.Servo;

class Claws {
    public Servo servo = null;
    public Claws(Servo servo) {
        this.servo = servo;
    }

    public void take(double compressionForce) {
        // TODO: calibrate
        servo.setPosition(0.5 + compressionForce*2);
    }

    public void leave() {
        servo.setPosition(0);
    }
}
