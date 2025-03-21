package org.firstinspires.ftc.team28420.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Vars {

    public enum Axis {
        X, Y, Z;
    }

    // MOVEMENT // TODO set names
    public final static String LEFT_FRONT_MOTOR_NAME = "topLeftMotor";
    public final static String RIGHT_FRONT_MOTOR_NAME = "topRightMotor";
    public final static String LEFT_BACK_MOTOR_NAME = "bottomLeftMotor";
    public final static String RIGHT_BACK_MOTOR_NAME = "bottomRightMotor";

    public final static int MAX_VELOCITY = 4500;

    public final static double WHEEL_DIAMETER = 10.0;

    // TODO: Calibrate
    // 35.5 is distance between front and rear wheel
    // 35.5 is distance between left and right wheel
    public final static double L_PLUS_W_CHASSIS = (35.5+35.5)/2;
    public final static double GEARBOX_RATIO = 2.89*3.61*5.23;
    public final static int TICKS_PER_WHEEL_TURN = (int) Math.round(28 * GEARBOX_RATIO);

    // GYROSCOPE //
    public final static RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    public final static RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    // UTIL //
    public static Telemetry telemetry;

}
