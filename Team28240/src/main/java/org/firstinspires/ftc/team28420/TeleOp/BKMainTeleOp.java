package org.firstinspires.ftc.team28420.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.eventsystem.EventType;
import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;
import org.firstinspires.ftc.team28420.modules.Joystick;
import org.firstinspires.ftc.team28420.util.Pos;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "bigkostyl test", group = "test")
public class BKMainTeleOp extends LinearOpMode {
    BigKostyl grabber = null;

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();

        while(opModeIsActive()) {
            grabber.handleControls(gamepad1, 0);

            grabber.updateTelemetry();
            telemetry.update();
        }
    }
    private void initHardware() {
        grabber = new BigKostyl(
                hardwareMap.get(DcMotor.class, "beltMotor"),
                hardwareMap.get(Servo.class, "wristServo"),
                hardwareMap.get(Servo.class, "clawServo"),
                telemetry);

        Vars.telemetry = telemetry;
    }


}
