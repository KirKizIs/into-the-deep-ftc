package org.firstinspires.ftc.team28420.module.grabber;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.team28420.util.Vars;

public class Belt {
    public final DcMotor motor;

    public Belt(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.motor = motor;
    }

    public void runToPosition(int position, boolean blocking) {
        motor.setTargetPosition(position);

        if(motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(Vars.Grabber.Belt.RUN_TO_TARGET_POWER);

        if(blocking) while(motor.isBusy());
    }

    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setVelocity(int velocity)  {
        if (Math.abs(velocity) > 0) {

            if (motor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER)
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            ((DcMotorEx) motor).setVelocity(velocity);
        }
        else if( Math.abs(velocity) == 0) {
            if (motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
                runToPosition(motor.getCurrentPosition(), false); // Hold current position
        }
    }

    public void toTakeFromWallPos() {
        runToPosition(Vars.Grabber.Belt.TAKE_FROM_WALL_POSITION, false);
    }

    public void toAquariumPos() {
        runToPosition(Vars.Grabber.Belt.AQUARIUM_POSITION, false);
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public void toDefaultPos() {
        runToPosition(Vars.Grabber.Belt.DEFAULT_POSITION, false);
    }

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }
    public int getTargetPosition() {return motor.getTargetPosition();}

}