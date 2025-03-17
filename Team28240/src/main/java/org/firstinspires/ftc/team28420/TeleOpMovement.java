package org.firstinspires.ftc.team28420;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.team28420.module.Gyroscope;
import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.Joystick;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "basic movement", group = "test")
public class TeleOpMovement extends LinearOpMode {

    Movement movement = new Movement(
            hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_FRONT_MOTOR_NAME),
            hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_FRONT_MOTOR_NAME),
            hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_BACK_MOTOR_NAME),
            hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_BACK_MOTOR_NAME));

    Gyroscope gyroscope = new Gyroscope(
            hardwareMap.get(BHI260IMU.class, "imu"));

    Joystick joystick = new Joystick(gamepad1);

    @Override
    public void runOpMode() {
        Vars.Util.telemetry = telemetry;

        movement.setup();
        gyroscope.setup();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
        }
    }

}
