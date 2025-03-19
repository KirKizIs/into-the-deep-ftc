package org.firstinspires.ftc.team28420.modules.bigkostyl;

//import org.firstinspires.ftc.team28420.EventSystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BigKostyl {
    public static Telemetry telemetry = null;
    private Belt belt = null;
    private Claws claws = null;
    private Wrist wrist = null;

    private double buttonCounter = 0;

    public BigKostyl(DcMotor beltMotor, Servo wristServo, Servo clawsServo, Telemetry telem) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
        BigKostyl.telemetry = telem;
    }

    public void setup() {
        belt.reset();
        wrist.reset();
    }

    public void updateTelemetry() {
        telemetry.addData("Belt motor position: ", belt.getCurrentMotorPosition());
        telemetry.addData("Wrist servo position: ", wrist.getCurrentServoPosition());
        telemetry.addData("Claws servo position: ", claws.getCurrentServoPosition());
    }
    public void handleControls(Gamepad gamepad, double dt) {
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
    }

}
