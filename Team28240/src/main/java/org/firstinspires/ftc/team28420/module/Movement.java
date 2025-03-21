package org.firstinspires.ftc.team28420.module;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.team28420.util.Vars;
import org.firstinspires.ftc.team28420.util.types.WheelsRatio;
import org.firstinspires.ftc.team28420.util.types.PolarVector;

public class Movement {

    public DcMotorEx leftFront, rightFront, leftBack, rightBack;

    public Movement(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack) {
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }

    public void setMotorsTargetPosition(WheelsRatio wheelsRatio) {
        wheelsRatio.multiply(Vars.Movement.MAX_VELOCITY);
        leftFront.setTargetPosition((int) wheelsRatio.leftFront);
        rightFront.setTargetPosition((int) wheelsRatio.rightFront);
        leftBack.setTargetPosition((int) wheelsRatio.leftBack);
        rightBack.setTargetPosition((int) wheelsRatio.rightBack);
    }

    public void setMotorsVelocities(WheelsRatio wheelsRatio) {
        leftFront.setVelocity(wheelsRatio.leftFront);
        rightFront.setVelocity(wheelsRatio.rightFront);
        leftBack.setVelocity(wheelsRatio.leftBack);
        rightBack.setVelocity(wheelsRatio.rightBack);
    }

    public void setMotorsMode(DcMotor.RunMode mode) {
        leftFront.setMode(mode);
        rightFront.setMode(mode);
        leftBack.setMode(mode);
        rightBack.setMode(mode);
    }

    public void setMotorsZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        leftFront.setZeroPowerBehavior(behavior);
        rightFront.setZeroPowerBehavior(behavior);
        leftBack.setZeroPowerBehavior(behavior);
        rightBack.setZeroPowerBehavior(behavior);
    }

    public boolean isBusy() {
        return leftFront.isBusy() || rightFront.isBusy() || leftBack.isBusy() || rightBack.isBusy();
    }

    public void setup() {
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        setMotorsMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorsMode(DcMotor.RunMode.RUN_USING_ENCODER);
        setMotorsZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void brake() {
        setMotorsVelocities(WheelsRatio.ZERO);
    }

    public WheelsRatio getTheta(PolarVector vector, double turn) {
        double sin = Math.sin(vector.theta - Math.PI / 4);
        double cos = Math.cos(vector.theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double lf = vector.abs * cos / max + turn;
        double rf = vector.abs * sin / max - turn;
        double lb = vector.abs * sin / max + turn;
        double rb = vector.abs * cos / max - turn;

        if ((vector.abs + Math.abs(turn)) > 1) {
            lf /= vector.abs + Math.abs(turn);
            rf /= vector.abs + Math.abs(turn);
            lb /= vector.abs + Math.abs(turn);
            lb /= vector.abs + Math.abs(turn);
        }

        return new WheelsRatio(lf, rf, lb, rb);
    }

}