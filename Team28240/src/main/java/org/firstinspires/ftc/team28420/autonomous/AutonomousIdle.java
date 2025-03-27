package org.firstinspires.ftc.team28420.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.grabber.Grabber;
import org.firstinspires.ftc.team28420.util.Vars;

@Autonomous(name = "auto idle", group = "autonomous")
public class AutonomousIdle extends LinearOpMode {

    private Movement movement;
    private Grabber grabber;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();
        grabber.intake.toDefaultPos();

        stop();
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

        Vars.Etc.telemetry = telemetry;
    }


    private void setup() {
        movement.setup();
        grabber.setup();
    }

}
