package org.firstinspires.ftc.team28420.teleops;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.module.Gyroscope;
import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.Joystick;
import org.firstinspires.ftc.team28420.module.grabber.Grabber;
import org.firstinspires.ftc.team28420.util.Vars;
import org.firstinspires.ftc.team28420.util.types.Axis;
import org.firstinspires.ftc.team28420.util.types.PolarVector;

@TeleOp(name = "TeleOpTest", group = "zzz")
public class MainTest extends LinearOpMode {

    private Movement movement;
    private Grabber grabber;
    private Gyroscope gyroscope;
    private Joystick stick;

    private boolean dpadUpHeld = false;
    private boolean dpadDownHeld = false;

    private boolean hold = false;
    DcMotor motor = null;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();
        while (opModeIsActive()) {
            if(stick.gamepad.right_bumper) Vars.Etc.coef = 0.3f;
            else Vars.Etc.coef = 1.f;

            if(stick.gamepad.left_stick_y > 0.3) stick.gamepad.setLedColor(255, 0, 0, Gamepad.LED_DURATION_CONTINUOUS);
            else if(stick.gamepad.left_stick_y < -0.3) stick.gamepad.setLedColor(0,  255, 0, Gamepad.LED_DURATION_CONTINUOUS);
            else stick.gamepad.setLedColor(255,  255, 255, Gamepad.LED_DURATION_CONTINUOUS);

            if(stick.gamepad.dpad_left) motor.setPower(0.4);
            else if(stick.gamepad.dpad_right) motor.setPower(-0.4);
            else motor.setPower(0);

            if(!hold && stick.gamepad.triangle) {
                motor.setPower(0.8);
                hold = true;
            } else if(stick.gamepad.triangle) hold = false;

            movementControls();
            clawsControls();
            wristControls();
            beltControls();

            telemetry.addData("", grabber.toString());
            telemetry.addData("", movement.toString());
            telemetry.update();
        }
    }

    public void initialize() {
        motor = hardwareMap.get(DcMotor.class, "motor1");
        movement = new Movement(
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_FRONT_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.LEFT_BACK_MOTOR_NAME),
                hardwareMap.get(DcMotorEx.class, Vars.Movement.RIGHT_BACK_MOTOR_NAME));
        grabber = new Grabber(
                hardwareMap.get(DcMotorEx.class, Vars.Grabber.Intake.MOTOR_NAME),
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
        movement.setMotorsVelocities(movement.getTheta(PolarVector.fromPos(
                stick.getLeftStickPos().multiply(Vars.Etc.coef)).rotate(-gyroscope.getAngle(Axis.Y)), Math.abs(
                gamepad1.right_stick_x) > 0.6 ? gamepad1.right_stick_x * Vars.Etc.coef : 0).multiply(Vars.Movement.MAX_VELOCITY));
    }

    private void clawsControls() {
        grabber.claws.setPosition(1-stick.gamepad.right_trigger);
    }

    private void wristControls() {
        double delta = 0.01 *
                ((stick.gamepad.left_trigger > 0 ? 1 : 0) +
                        (stick.gamepad.left_bumper ? -1 : 0));
        grabber.wrist.setPosition(grabber.wrist.servo.getPosition() + delta);

        if(stick.gamepad.triangle) grabber.wrist.toTopPos();
        else if(stick.gamepad.circle) grabber.wrist.turnToStraight();
        else if(stick.gamepad.cross) grabber.wrist.turnToBottom();
    }

    private void beltControls() {
        if (stick.gamepad.dpad_up && !dpadUpHeld) {
            grabber.intake.toTakeFromWallPos();

            grabber.wrist.setPosition(Vars.Grabber.Wrist.TAKE_FROM_WALL_POSITION);

            dpadUpHeld = true;
        } else if(!stick.gamepad.dpad_up && dpadUpHeld) dpadUpHeld = false;

        if (stick.gamepad.dpad_down && !dpadDownHeld) {
            grabber.intake.toAquariumPos();

            grabber.wrist.setPosition(Vars.Grabber.Wrist.AQUARIUM_POSITION);

            dpadDownHeld = true;
        } else if(!stick.gamepad.dpad_down && dpadDownHeld) dpadDownHeld = false;

        if(stick.gamepad.square) grabber.intake.resetEncoder();

        grabber.intake.setVelocity(Math.round(-stick.gamepad.right_stick_y * Vars.Etc.coef *
                Vars.Grabber.Intake.INPUT_COEFFICIENT));
    }
}
