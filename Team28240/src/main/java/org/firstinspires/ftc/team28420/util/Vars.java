package org.firstinspires.ftc.team28420.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Vars {

    // TODO set names
    public static class Movement {
        public final static String LEFT_FRONT_MOTOR_NAME = "";
        public final static String RIGHT_FRONT_MOTOR_NAME = "";
        public final static String LEFT_BACK_MOTOR_NAME = "";
        public final static String RIGHT_BACK_MOTOR_NAME = "";

        public final static int MAX_VELOCITY = 5500;
    }

    public static class Gyroscope {
        public final static RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public final static RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    public static class Util {
        public static Telemetry telemetry;
    }

}
