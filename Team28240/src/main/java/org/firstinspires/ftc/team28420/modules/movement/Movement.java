package org.firstinspires.ftc.team28420.modules.movement;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.util.PolarVector;
import org.firstinspires.ftc.team28420.util.Pos;
import org.firstinspires.ftc.team28420.util.Vars;
import org.firstinspires.ftc.team28420.util.WheelsRatio;

public class Movement {

    public DcMotorEx leftFront, rightFront, leftBack, rightBack;

    public Movement(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack) {
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }

    public void setMotorsTargetPosition(WheelsRatio wheelsRatio) {
        wheelsRatio.multiply(Vars.MAX_VELOCITY);
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

    public WheelsRatio getTheta(double theta, double power, double turn) {
        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double lf = power * cos / max + turn;
        double rf = power * sin / max - turn;
        double lb = power * sin / max + turn;
        double rb = power * cos / max - turn;

        if ((power + Math.abs(turn)) > 1) {
            lf /= power + Math.abs(turn);
            rf /= power + Math.abs(turn);
            lb /= power + Math.abs(turn);
            lb /= power + Math.abs(turn);
        }

        return new WheelsRatio(lf, rf, lb, rb);
    }

    public void move(Pos position, double rotation) {
        PolarVector vector = PolarVector.getVectorFromPos(position);
        setMotorsVelocities(getTheta(vector.theta, vector.abs, rotation).multiply(Vars.MAX_VELOCITY));
    }

    public void handleControls(Gamepad gamepad) {
        if(Math.abs(gamepad.left_stick_x) < 0.2) gamepad.left_stick_x = 0;
        if(Math.abs(gamepad.left_stick_y) < 0.2) gamepad.left_stick_y = 0;
        if(Math.abs(gamepad.right_stick_x) < 0.4) gamepad.right_stick_x = 0;


        move(new Pos(
            gamepad.right_bumper?gamepad.left_stick_x/3:gamepad.left_stick_x,
            gamepad.right_bumper?-gamepad.left_stick_y/3:-gamepad.left_stick_y),
            gamepad.right_bumper?gamepad.right_stick_x/3:gamepad.right_stick_x
        );
    }
}