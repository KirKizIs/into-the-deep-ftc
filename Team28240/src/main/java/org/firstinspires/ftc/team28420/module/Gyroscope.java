package org.firstinspires.ftc.team28420.module;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.hardware.IMU;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import org.firstinspires.ftc.team28420.util.Axes;

public class Gyroscope {

    private BHI260IMU imu;

    //TODO move this to const vars class
    public final RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    public final RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    public Gyroscope(BHI260IMU imu) {
        this.imu = imu;
    }

    public void init() {
        IMU.Parameters orientation = new IMU.Parameters(
                new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection));
        imu.initialize(orientation);
        imu.resetYaw();
    }

    public YawPitchRollAngles getOrientation() {
        return imu.getRobotYawPitchRollAngles();
    }
    public double getAngle(Axes axis) {
        switch (axis) {
            case X:
                return imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS);
            case Y:
                return imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.RADIANS);
            case Z:
                return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        YawPitchRollAngles orientation = getOrientation();
        return "ANGLES: {" +
                " X: " + orientation.getPitch(AngleUnit.RADIANS) +
                " Y: " + orientation.getRoll(AngleUnit.RADIANS) +
                " Z: " + orientation.getYaw(AngleUnit.RADIANS) +
                " }";
    }

}
