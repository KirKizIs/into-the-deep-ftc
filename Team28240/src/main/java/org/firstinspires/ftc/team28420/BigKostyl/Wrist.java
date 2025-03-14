package org.firstinspires.ftc.team28420.BigKostyl;

import com.qualcomm.robotcore.hardware.Servo;

class Wrist {
    public Servo servo = null;

    private static double TURNED_POSITION = 0.5;
    private static double START_POSITION = 0;
    public Wrist(Servo servo) {
        this.servo = servo;
    }

    public void turnDown() {
        servo.setPosition(TURNED_POSITION);
    }

    public void turnStraight() {
        servo.setPosition(START_POSITION);
    }

    public double getCurrentServoPosition() {
        return servo.getPosition();
    }
}
