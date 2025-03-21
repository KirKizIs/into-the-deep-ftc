package org.firstinspires.ftc.team28420.autonomous;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team28420.modules.Gyroscope;
import org.firstinspires.ftc.team28420.modules.bigkostyl.BigKostyl;
import org.firstinspires.ftc.team28420.modules.movement.Movement;
import org.firstinspires.ftc.team28420.util.Vars;

@Autonomous(name = "blue autonomous", group = "Autonomous")
public class BlueAutonomous extends LinearOpMode {
    public BigKostyl grabber = null;
    Movement movement = null;
    Gyroscope gyroscope = null;

    @Override
    public void runOpMode() {
        double dt = 0;
        ElapsedTime et = new ElapsedTime();

        initHardware();

        waitForStart();

        while(opModeIsActive()) {

            movement.passDistanceForward(100);

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
