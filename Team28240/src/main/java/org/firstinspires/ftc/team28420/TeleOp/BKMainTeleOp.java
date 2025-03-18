package org.firstinspires.ftc.team28420.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;
import org.firstinspires.ftc.team28420.modules.Joystick;

@TeleOp(name = "bigkostyl test", group = "test")
public class BKMainTeleOp extends LinearOpMode {
    BigKostyl grabber = null;

    Joystick joystick = null;

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();

        while(opModeIsActive()) {
            joystick.update();
            grabber.updateTelemetry();
        }
    }
    private void initHardware() {
        joystick = new Joystick(gamepad1);
        grabber = new BigKostyl(
                hardwareMap.get(DcMotor.class, "beltMotor"),
                hardwareMap.get(Servo.class, "wristServo"),
                hardwareMap.get(Servo.class, "clawServo"),
                telemetry);
    }


}
