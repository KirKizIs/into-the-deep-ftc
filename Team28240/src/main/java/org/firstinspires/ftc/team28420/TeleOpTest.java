package org.firstinspires.ftc.team28420;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.module.Gyroscope;
import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.Joystick;
import org.firstinspires.ftc.team28420.module.grabber.Grabber;
import org.firstinspires.ftc.team28420.util.Utility;
import org.firstinspires.ftc.team28420.util.Vars;

@TeleOp(name = "test", group = "test")
public class TeleOpTest extends LinearOpMode {

    private Movement movement;
    private Grabber grabber;
    private Gyroscope gyroscope;
    private Joystick stick;

    private boolean dpadUpHeld = false;
    private boolean dpadDownHeld = false;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();
        while (opModeIsActive()) {
            movementControls();
            clawsControls();
            wristControls();
            beltControls();

            telemetry.addData("", grabber.toString());
            telemetry.update();
        }
    }

    public void initialize() {
           movement = new Movement(
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_BACK_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_BACK_MOTOR_NAME));
        grabber = new Grabber(
                hardwareMap.get(DcMotorEx.class, Vars.Grabber.Belt.MOTOR_NAME),
                hardwareMap.get(Servo.class, Vars.Grabber.Wrist.SERVO_NAME),
                hardwareMap.get(Servo.class, Vars.Grabber.Claws.SERVO_NAME));
        gyroscope = new Gyroscope(
                hardwareMap.get(BHI260IMU.class, "imu"));
        Vars.Etc.telemetry = telemetry;

        stick = new Joystick(gamepad1);
    }


    private void setup() {
        movement.setup();
        gyroscope.setup();
    }

    private void movementControls() {
        movement.setMotorsVelocities(movement.getTheta(Utility.getVectorFromPos(
                        stick.getLeftStickPos()), gamepad1.right_stick_x>0.3?gamepad1.right_stick_x:0).multiply(Vars.Movement.MAX_VELOCITY));
    }

    private void clawsControls() {
        grabber.claws.setPosition(1-stick.gamepad.right_trigger);
    }

    private void wristControls() {
        double delta = 0.01 *
                ((stick.gamepad.left_trigger > 0 ? 1 : 0) +
                        (stick.gamepad.left_bumper ? -1 : 0));
        telemetry.addData("wrist", delta);
        grabber.wrist.setPosition(grabber.wrist.servo.getPosition() + delta);
    }

    private void beltControls() {
        if (stick.gamepad.dpad_up && !dpadUpHeld) {
            grabber.belt.nextPosition();
            dpadUpHeld = true;
        } else if(!stick.gamepad.dpad_up && dpadUpHeld) dpadUpHeld = false;

        if (stick.gamepad.dpad_down && !dpadDownHeld) {
            grabber.belt.prevPosition();
            dpadDownHeld = true;
        } else if(!stick.gamepad.dpad_down && dpadDownHeld) dpadDownHeld = false;

        //if (!grabber.belt.isBusy()) {
            grabber.belt.setVelocity(Math.round(-stick.gamepad.right_stick_y *
                    Vars.Grabber.Belt.INPUT_COEFFICIENT));
        //}
    }
}
