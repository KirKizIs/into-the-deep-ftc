package org.firstinspires.ftc.team28420.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;

@TeleOp(name = "bk main teleop", group = "test")
public class BKMainTeleOp extends LinearOpMode {
    public BigKostyl grabber = null;
    @Override
    public void runOpMode() {
        double dt = 0;
        ElapsedTime et = new ElapsedTime();

        initHardware();

        waitForStart();

        boolean lbPressed = false;

        while(opModeIsActive()) {
            grabber.handleControls(gamepad1, dt);
            grabber.updateTelemetry();

            telemetry.update();

            dt = et.milliseconds();
            et.reset();
        }
    }
    private void initHardware() {
        grabber = new BigKostyl(
                hardwareMap.get(DcMotor.class, "beltMotor"),
                hardwareMap.get(Servo.class, "wristServo"),
                hardwareMap.get(Servo.class, "clawServo"),
                telemetry);

        //  grabber.setup();
    }


}
