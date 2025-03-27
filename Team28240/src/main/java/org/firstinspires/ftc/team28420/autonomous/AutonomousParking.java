package org.firstinspires.ftc.team28420.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team28420.module.Movement;
import org.firstinspires.ftc.team28420.module.grabber.Grabber;
import org.firstinspires.ftc.team28420.util.Vars;
import org.firstinspires.ftc.team28420.util.types.PolarVector;

@Autonomous(name = "auto parking", group = "autonomous")
public class AutonomousParking extends LinearOpMode {

    private Movement movement;
    private Grabber grabber;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();
        ElapsedTime ep = new ElapsedTime();

        while(ep.milliseconds() <= 250 && opModeIsActive())
            movement.setMotorsVelocities(movement.getTheta(new PolarVector(3*Math.PI/2, 0.1), 0).multiply(Vars.Movement.MAX_VELOCITY));
        ep.reset();
        while(ep.seconds() <= 4 && opModeIsActive())
            movement.setMotorsVelocities(movement.getTheta(new PolarVector(Math.PI, 0.1), 0).multiply(Vars.Movement.MAX_VELOCITY));
        ep.reset();
        while(ep.milliseconds() <= 250 && opModeIsActive())
            movement.setMotorsVelocities(movement.getTheta(new PolarVector(Math.PI/2, 0.1), 0).multiply(Vars.Movement.MAX_VELOCITY));
        grabber.intake.runToPosition(0, true);
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
