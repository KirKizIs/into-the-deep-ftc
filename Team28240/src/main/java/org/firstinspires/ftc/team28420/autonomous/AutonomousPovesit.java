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
import org.firstinspires.ftc.team28420.util.types.WheelsRatio;

@Autonomous(name = "auto povesit", group = "autonomous")
public class AutonomousPovesit extends LinearOpMode {

    private Movement movement;
    private Grabber grabber;

    @Override
    public void runOpMode() {
        initialize();
        setup();
        waitForStart();

        grabber.intake.runToPosition(-900, false);
        grabber.wrist.setPosition(0.65);
        grabber.claws.take();

        movement.runToEncoderPos(new WheelsRatio(1060, 1060, 1060, 1060), 3000, true);
        grabber.intake.runToPosition(-1400, true);

        grabber.claws.leave();

        ElapsedTime ep = new ElapsedTime();
        while(ep.seconds() <= 2 && opModeIsActive())
            movement.setMotorsVelocities(movement.getTheta(new PolarVector(-Math.PI/4, 0.2), 0).multiply(Vars.Movement.MAX_VELOCITY));

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
                hardwareMap.get(DcMotorEx.class, Vars.Grabber.Intake.MOTOR_NAME),
                hardwareMap.get(Servo.class, Vars.Grabber.Wrist.SERVO_NAME),
                hardwareMap.get(Servo.class, Vars.Grabber.Claws.SERVO_NAME));

        Vars.Etc.telemetry = telemetry;
    }


    private void setup() {
        movement.setup();
        grabber.setup();
    }

}
