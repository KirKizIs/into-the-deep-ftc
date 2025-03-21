package org.firstinspires.ftc.team28420.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public final class Vars {

    public final static class Movement {
        public final static String LEFT_FRONT_MOTOR_NAME = "movementMotorTopLeft";
        public final static String RIGHT_FRONT_MOTOR_NAME = "movementMotorTopRight";
        public final static String LEFT_BACK_MOTOR_NAME = "movementMotorBottomLeft";
        public final static String RIGHT_BACK_MOTOR_NAME = "movementMotorBottomRight";

        public final static int MAX_VELOCITY = 5500;
    }

    public final static class Gyroscope {
        public final static double[] CONST_ANGLES = {0, 45, 90, 135, 180, 225, 270, 315};

        public final static RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public final static RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    public final static class Grabber {
        // TODO: add names
        public final static class Claws {
            public static final String SERVO_NAME = "";

            public static final double TAKE_POSITION = 0.069; // TODO: calibrate
            public static final double LEAVE_POSITION = 0.5;
        }

        public final static class Wrist { // TODO: calibrate

            public final static String SERVO_NAME = "";
            public final static double DELTA_POSITION = 0.05;

            public final static double TOP_POSITION = 0;

            public final static double STRAIGHT_POSITION = 0.5;
            public final static double BOTTOM_POSITION = 1;
            public final static double START_POSITION = 0;
        }

        public final static class Belt {
            public static final String MOTOR_NAME = "";

            public static final int HIGHEST_POSITION = -400;
            public static final int MIDDLE_POSITION = -250;
            public static final int LOWEST_POSITION = -150;
            public static final double RUN_TO_TARGET_POWER = 0.4;

            // TODO: Calibrate positions
            public static final int[] BOUND_POSITIONS = {LOWEST_POSITION, MIDDLE_POSITION, HIGHEST_POSITION};

            public static final int INPUT_COEFFICIENT = 2000;
        }
    }

    public final static class Etc {
        public static Telemetry telemetry;
    }

}
