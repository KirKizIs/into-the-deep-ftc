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


    // Distance is given in centimeters
    public void passDistanceForward(double distance){
        int desiredTicks = (int) Math.round((Vars.TICKS_PER_WHEEL_TURN * distance)/(Math.PI* Vars.WHEEL_DIAMETER));

        setMotorsTargetPosition(new WheelsRatio(desiredTicks + leftFront.getCurrentPosition(),
                desiredTicks + rightFront.getCurrentPosition(),
                desiredTicks + leftBack.getCurrentPosition(),
                desiredTicks + rightBack.getCurrentPosition()));
        setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorsVelocities(new WheelsRatio(Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY));

        while(isBusy());
    }
    public void passTurn(double angle){
        /*
         * Ω1=R1(Vx−Vy−(L+W)ω)
         * Ω2=1R(Vx+Vy+(L+W)ω)
         * Ω3=1R(Vx+Vy−(L+W)ω)
         * Ω4=1R(Vx−Vy+(L+W)ω)
         * */

        double R = Vars.WHEEL_DIAMETER/2.0;
        double angleToRad = Math.toRadians(angle);

        int omega = (int) ((1/R * (Vars.L_PLUS_W_CHASSIS * angleToRad)) / (2 * Math.PI) * Vars.TICKS_PER_WHEEL_TURN);

        setMotorsTargetPosition(new WheelsRatio(-omega + leftFront.getCurrentPosition(),
                omega + rightFront.getCurrentPosition(),
                -omega + leftBack.getCurrentPosition(),
                omega + rightBack.getCurrentPosition()));
        setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorsVelocities(new WheelsRatio(Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY));

        while(isBusy());
    }

    public void passArc(double angle, double distance){
        double R = Vars.WHEEL_DIAMETER/2.0;
        double angleToRad = Math.toRadians(angle);

        int desiredTicks = (int) Math.round((Vars.TICKS_PER_WHEEL_TURN * distance)/(Math.PI* Vars.WHEEL_DIAMETER));
        int omega = (int) ((1/R * (Vars.L_PLUS_W_CHASSIS * angleToRad)) / (2 * Math.PI) * Vars.TICKS_PER_WHEEL_TURN);

        setMotorsTargetPosition(new WheelsRatio(-omega + desiredTicks + leftFront.getCurrentPosition(),
                omega + desiredTicks + rightFront.getCurrentPosition(),
                -omega + desiredTicks + leftBack.getCurrentPosition(),
                omega + desiredTicks + rightBack.getCurrentPosition()));

        setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorsVelocities(new WheelsRatio(Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY,
                Vars.MAX_VELOCITY));

        while(isBusy());
    }
}