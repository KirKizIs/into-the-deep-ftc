package org.firstinspires.ftc.team28420.modules.bigkostyl;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

class Belt {
    public DcMotor motor = null;

    public final int HIGHEST_POSITION = -400;
    public final int MIDDLE_POSITION = -250;
    public final int LOWEST_POSITION = -150;
    public final double RUN_TO_TARGET_POWER = 0.4;
    public static final int ROTATION_SPEED = 1400;

    // TODO: Calibrate positions
    private final int[] boundPositions = {LOWEST_POSITION, MIDDLE_POSITION, HIGHEST_POSITION};
    private int currentPosition = 0;

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

        motor.setPower(RUN_TO_TARGET_POWER);
    }
    public void setVelocity(int velocity)  {
        if(Math.abs(velocity) > 0) {
            if (motor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER)
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            ((DcMotorEx) motor).setVelocity(velocity);

        } else if(Math.abs(velocity) == 0) {
            if(motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
                // Hold current position
                runToPosition(motor.getCurrentPosition());
        }
    }

    private void bindToCurrentPosition() {
        runToPosition(boundPositions[currentPosition]);
    }
    public void nextPosition() {
        currentPosition += 1;
        if(currentPosition == boundPositions.length)
            currentPosition -= boundPositions.length;

        bindToCurrentPosition();
    }
    public void prevPosition() {
        currentPosition -= 1;
        if(currentPosition == -1)
            currentPosition += boundPositions.length;

        bindToCurrentPosition();
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    // Returns Belt to its start position which TeleOp sets up in the beginning
    public void reset() {
        runToPosition(LOWEST_POSITION);
    }

    public double getCurrentMotorPosition() {
        return motor.getCurrentPosition();
    }

}
