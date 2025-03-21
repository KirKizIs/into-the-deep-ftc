package org.firstinspires.ftc.team28420.modules.bigkostyl;

import com.qualcomm.robotcore.hardware.Servo;

class Claws {
    public Servo servo = null;

    public static final double CLOSED_SERVO_POSITION = 0.1;
    public static final double RELEASED_SERVO_POSITION = 0.5;

    public Claws(Servo servo) {
        this.servo = servo;
    }

    public void take() {
        servo.setPosition(CLOSED_SERVO_POSITION);
    }

    public void leave() {
        servo.setPosition(RELEASED_SERVO_POSITION);
    }

    public double getCurrentServoPosition() {
        return servo.getPosition();
    }
}
