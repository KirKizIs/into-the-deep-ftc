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
        public final static double MAX_ANGLE_ROTATE = Math.PI;

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
            public static final double TAKE_POSITION = 0.069; // TODO: calibrate
            public static final double LEAVE_POSITION = 0.5;
        }

        public final static class Belt {
            public final int HIGHEST_POSITION = -400;
            public final int MIDDLE_POSITION = -250;
            public final int LOWEST_POSITION = -150;

            public final double RUN_TO_TARGET_POWER = 0.4;
            public final int UPPER_LIMIT_POSITION = -376; // CALIBRATE

            // START_POSITION is a limit so the belt doesn't destroy Control Hub
            public final int LOWER_LIMIT_POSITION = -100;
            private final int ENCODER_ERROR_OFFSET = 5;
            public static final int ROTATION_SPEED = 900;

            // TODO: Calibrate positions
            private final int[] boundPositions = {LOWEST_POSITION, MIDDLE_POSITION, HIGHEST_POSITION};
        }

        public final static class Wrist { // TODO: calibrate
            public final static double TOP_POSITION = 0;
            public final static double STRAIGHT_POSITION = 0.5;
            public final static double BOTTOM_POSITION = 1;
            public final static double START_POSITION = 0;
        }
    }

    public final static class Etc {
        public static Telemetry telemetry;
    }

}
