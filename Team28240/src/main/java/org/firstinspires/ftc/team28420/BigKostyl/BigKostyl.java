package org.firstinspires.ftc.team28420.BigKostyl;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BigKostyl {

    private static Telemetry telemetry = null;
    private Belt belt = null;
    private Claws claws = null;
    private Wrist wrist = null;

    public BigKostyl(DcMotor beltMotor, Servo wristServo, Servo clawsServo, Telemetry telemetry) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
    }

    public void handleJoystick(Gamepad gamepad) {

    }
}
