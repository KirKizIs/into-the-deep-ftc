package org.firstinspires.ftc.team28420.module;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.hardware.IMU;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import org.firstinspires.ftc.team28420.util.Vars;
import org.firstinspires.ftc.team28420.util.types.Axis;

public class Gyroscope {

    private final BHI260IMU imu;

    public Gyroscope(BHI260IMU imu) {
        this.imu = imu;
    }

    public void setup() {
        IMU.Parameters orientation = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        Vars.Gyroscope.logoFacingDirection, Vars.Gyroscope.usbFacingDirection));
        imu.initialize(orientation);
        imu.resetYaw();
    }

    public YawPitchRollAngles getOrientation() {
        return imu.getRobotYawPitchRollAngles();
    }

    public double getAngle(Axis axis) {
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

    // TODO: think about the name of the method
    public double getAngleToClosestConstNumber() {
        double targetAngle = Vars.Gyroscope.CONST_ANGLES[((int)
                (getAngle(Axis.Z) + 25) % 360 / 45)];
        return targetAngle;
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
