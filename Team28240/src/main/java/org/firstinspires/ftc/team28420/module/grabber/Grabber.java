package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber {

    private final Belt belt;
    private final Claws claws;
    private final Wrist wrist;

    public Grabber(DcMotor beltMotor, Servo wristServo, Servo clawsServo) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
    }

    public void setup() {
        belt.reset();
        wrist.reset();
    }

    // TODO: remake to another class
/*    public void handleControls(Gamepad gamepad, double dt) {
        if(Math.abs(gamepad.right_stick_y) > 0.1)
            belt.setVelocity((int) (gamepad.right_stick_y * Belt.ROTATION_SPEED));
        else belt.setVelocity(0);

        if(gamepad.triangle)
            wrist.turnUp();
        if(gamepad.circle)
            wrist.turnStraight();
        if(gamepad.cross)
            wrist.turnDown();
        if(gamepad.square)
            belt.reset();

        if(gamepad.dpad_up && buttonCounter > 0.1) {
            belt.nextPosition();
            buttonCounter = 0;
        }

        if(gamepad.dpad_down && buttonCounter > 0.1) {
            belt.prevPosition();
            buttonCounter = 0;
        }

        if(gamepad.right_trigger > 0)
            claws.take();
        else claws.leave();

        buttonCounter += dt;
    }*/

    @Override
    public String toString() {
        return "Belt motor position: " + belt.getCurrentMotorPosition() +
                " Wrist servo position: " + wrist.servo.getPosition() +
                " Claws servo position: " + claws.servo.getPosition();
    }
}