package org.firstinspires.ftc.team28420;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team28420.Modules.BigKostyl.BigKostyl;
import org.firstinspires.ftc.team28420.Modules.Joystick;

@TeleOp(name = "main teleop", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    public static Telemetry telem;
    public BigKostyl grabber = null;

    Joystick joystick;

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();


        while(opModeIsActive()) {
            joystick.update();

        }
    }
    private void initHardware() {
        grabber = new BigKostyl(
                hardwareMap.get(DcMotor.class, "beltMotor"),
                hardwareMap.get(Servo.class, "wristServo"),
                hardwareMap.get(Servo.class, "clawServo"),
                telem);
    }


}
