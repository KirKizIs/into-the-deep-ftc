package org.firstinspires.ftc.team28420.TeleOp;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;
import org.firstinspires.ftc.team28420.modules.Joystick;
import org.firstinspires.ftc.team28420.modules.movement.Gyroscope;
import org.firstinspires.ftc.team28420.modules.movement.Movement;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "main teleop", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    public BigKostyl grabber = null;
    Movement movement = null;

    Gyroscope gyroscope = null;

    Joystick joystick;

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

        movement = new Movement(
                hardwareMap.get(DcMotorEx.class, Vars.LEFT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.RIGHT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.LEFT_BACK_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.RIGHT_BACK_MOTOR_NAME));

        gyroscope = new Gyroscope(
                hardwareMap.get(BHI260IMU.class, "imu"));

        movement.setup();
        gyroscope.setup();
    }


}
