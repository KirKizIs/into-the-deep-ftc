package org.firstinspires.ftc.team28420.TeleOp;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;
import org.firstinspires.ftc.team28420.modules.Gyroscope;
import org.firstinspires.ftc.team28420.modules.movement.Movement;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "main teleop", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    public BigKostyl grabber = null;
    Movement movement = null;

    Gyroscope gyroscope = null;

    DcMotorEx motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
    DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class, "motor2");

    @Override
    public void runOpMode() {
        double dt = 0;
        ElapsedTime et = new ElapsedTime();

        initHardware();

        waitForStart();

        boolean lbPressed = false;

        while(opModeIsActive()) {
            grabber.handleControls(gamepad1, dt);
            movement.handleControls(gamepad1);
            grabber.updateTelemetry();

            if(gamepad1.left_bumper && lbPressed == false) {
                if (motor1.getTargetPosition() == 10000 && motor2.getTargetPosition() == 10000) {
                    motor1.setTargetPosition(0);
                    motor2.setTargetPosition(0);
                    motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                } else {
                    motor1.setTargetPosition(10000);
                    motor2.setTargetPosition(10000);
                    motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                lbPressed = true;
            }
            if(!gamepad1.left_bumper) lbPressed = false;

            motor1.setVelocity(gamepad1.left_trigger*2000);
            motor2.setVelocity(gamepad1.left_trigger*2000);


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

        movement = new Movement(
                hardwareMap.get(DcMotorEx.class, Vars.LEFT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.RIGHT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.LEFT_BACK_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.RIGHT_BACK_MOTOR_NAME));

        gyroscope = new Gyroscope(
                hardwareMap.get(BHI260IMU.class, "imu"));

        grabber.setup();
        movement.setup();
        gyroscope.setup();
    }


}
