package org.firstinspires.ftc.team28420;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.team28420.module.Gyroscope;
import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.Joystick;
import org.firstinspires.ftc.team28420.util.Utility;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "basic movement", group = "test")
public class TeleOpMovement extends LinearOpMode {

    Movement movement;
    Gyroscope gyroscope;
    Joystick stick;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();
        while (opModeIsActive()) {
            movement();
            telemetry.update();
        }
    }

    public void initialize() {
        movement = new Movement(
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_BACK_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_BACK_MOTOR_NAME));
        gyroscope = new Gyroscope(
                hardwareMap.get(BHI260IMU.class, "imu"));
        Vars.Etc.telemetry = telemetry;

        stick = new Joystick(gamepad1);
    }


    private void setup() {
        movement.setup();
        gyroscope.setup();
    }

    private void movement() {
        if (stick.gamepad.left_stick_button) {

        }
        movement.setMotorsVelocities(movement.getTheta(Utility.getVectorFromPos(
                        stick.getLeftStickPos()),
                gamepad1.right_stick_x).multiply(Vars.Movement.MAX_VELOCITY));
    }

}
