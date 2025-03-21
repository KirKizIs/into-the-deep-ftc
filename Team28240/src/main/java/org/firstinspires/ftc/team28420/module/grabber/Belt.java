package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.team28420.util.Vars;

public class Belt {
    public final DcMotor motor;
    private int currentState = 0;

    public Belt(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.motor = motor;
    }

    public void runToPosition(int position) {
        motor.setTargetPosition(position);

        if(motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(Vars.Grabber.Belt.RUN_TO_TARGET_POWER);
    }

    public void setVelocity(int velocity)  {
        if (Math.abs(velocity) > 0) {
            if (motor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER)
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            ((DcMotorEx) motor).setVelocity(velocity);
        }
        else if( Math.abs(velocity) == 0) {
            if (motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
                runToPosition(motor.getCurrentPosition()); // Hold current position
        }
    }

    public void nextPosition() {
        currentState += 1;
        if(currentState == Vars.Grabber.Belt.BOUND_POSITIONS.length)
            currentState -= Vars.Grabber.Belt.BOUND_POSITIONS.length;
        runToPosition(Vars.Grabber.Belt.BOUND_POSITIONS[currentState]);
    }

    public void prevPosition() {
        currentState -= 1;
        if(currentState == -1)
            currentState += Vars.Grabber.Belt.BOUND_POSITIONS.length;
        runToPosition(Vars.Grabber.Belt.BOUND_POSITIONS[currentState]);
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public void reset() {
        runToPosition(Vars.Grabber.Belt.LOWEST_POSITION);
    }

    public double getCurrentMotorPosition() {
        return motor.getCurrentPosition();
    }

}