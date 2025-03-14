package org.firstinspires.ftc.team28420.BigKostyl;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BigKostyl {

    public static Telemetry telemetry = null;
    private Belt belt = null;
    private Claws claws = null;
    private Wrist wrist = null;

    public BigKostyl(DcMotor beltMotor, Servo wristServo, Servo clawsServo, Telemetry telemetry) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
    }

    void updateTelemetry() {
        telemetry.addData("Belt motor position: ", belt.getCurrentMotorPosition());
        telemetry.addData("Claws servo position: ", claws.getCurrentServoPosition());
        telemetry.addData("Wrist servo position: ", claws.getCurrentServoPosition());

        telemetry.update();
    }

    public void handleJoystick(Gamepad gamepad) {

    }
}
