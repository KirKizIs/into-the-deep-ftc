package org.firstinspires.ftc.team28420.modules.bigkostyl;

import com.qualcomm.robotcore.hardware.Servo;

class Wrist {
    public Servo servo = null;

    private final static double UP_POSITION = 0.6;
    private final static double STRAIGHT_POSITION = 0.3;
    private final static double DOWN_POSITION = 0;
    public Wrist(Servo servo) {
        this.servo = servo;
    }

    public void turnUp() { servo.setPosition(UP_POSITION); }
    public void turnStraight() { servo.setPosition(STRAIGHT_POSITION); }
    public void turnDown() {
        servo.setPosition(DOWN_POSITION);
    }
    public double getCurrentServoPosition() { return servo.getPosition(); }

}
