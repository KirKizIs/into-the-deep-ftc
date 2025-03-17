package org.firstinspires.ftc.team28420.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Vars {

    public static enum Axis {
        X, Y, Z;
    }

    public static class Pos {
        public double x, y;
        public Pos(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Vector {
        public double theta, abs;
        public Vector(double theta, double abs) {
            this.theta = theta;
            this.abs = abs;
        }
    }

    // MOVEMENT // TODO set names
    public final static String LEFT_FRONT_MOTOR_NAME = "";
    public final static String RIGHT_FRONT_MOTOR_NAME = "";
    public final static String LEFT_BACK_MOTOR_NAME = "";
    public final static String RIGHT_BACK_MOTOR_NAME = "";

    public final static int MAX_VELOCITY = 5500;

    // GYROSCOPE //
    public final static RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    public final static RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    // UTIL //
    public static Telemetry telemetry;

}
