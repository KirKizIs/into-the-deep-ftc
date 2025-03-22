package org.firstinspires.ftc.team28420.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public final class Vars {
    public final static class Movement {
        public final static String LEFT_FRONT_MOTOR_NAME = "topLeftMotor";
        public final static String RIGHT_FRONT_MOTOR_NAME = "topRightMotor";
        public final static String LEFT_BACK_MOTOR_NAME = "bottomLeftMotor";
        public final static String RIGHT_BACK_MOTOR_NAME = "bottomRightMotor";

        public final static int MAX_VELOCITY = 4600;

        public final static double WHEEL_DIAMETER = 10.0;

        // 35.5 is distance between front and rear wheel
        // 35.5 is distance between left and right wheel
        public final static double L_PLUS_W_CHASSIS = (35.5+35.5)/2;
        public final static double GEARBOX_RATIO = 3.61*5.23;
        public final static int TICKS_PER_WHEEL_TURN = (int) Math.round(28 * GEARBOX_RATIO);
    }

    public final static class Gyroscope {
        public final static double[] CONST_ANGLES = {0, 45, 90, 135, 180, 225, 270, 315};

        public final static RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public final static RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    public final static class Grabber {
        public final static class Claws {
            public static final String SERVO_NAME = "clawServo";
            public static final double MAX_POSITION = 0.5;
            public static final double MIN_POSITION = 0.2;
        }

        public final static class Wrist {
            public final static String SERVO_NAME = "wristServo";
            public final static double TAKE_FROM_WALL_POSITION = 0.45;
            public final static double AQUARIUM_POSITION = 0.74;

            public final static double UP_POSITION = 0;
            public final static double STRAIGHT_POSITION = 0.4;
            public final static double BOTTOM_POSITION = 0.76;
            public final static double START_POSITION = 0.9;
        }

        public final static class Belt {
            public static final String MOTOR_NAME = "beltMotor";
            public static final int DEFAULT_POSITION = -360;
            public static final int TAKE_FROM_WALL_POSITION = -1618;
            public static final int AQUARIUM_POSITION = -1700;
            public static final double RUN_TO_TARGET_POWER = 1;

            public static final int[] BOUND_POSITIONS = {AQUARIUM_POSITION, TAKE_FROM_WALL_POSITION};

            public static final int INPUT_COEFFICIENT = 2000;
        }
    }

    public final static class Etc {
        public static Telemetry telemetry;
        public static float coef = 1;
    }

}
