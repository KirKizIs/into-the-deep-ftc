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
    public final int UPPER_LIMIT_POSITION = -376; // CALIBRATE

    // START_POSITION is a limit so the belt doesn't destroy Control Hub
    public final int LOWER_LIMIT_POSITION = -100;
    private final int ENCODER_ERROR_OFFSET = 5;
    public static final int ROTATION_SPEED = 900;

    // TODO: Calibrate positions
    private final int[] boundPositions = {LOWEST_POSITION, MIDDLE_POSITION, HIGHEST_POSITION};
    private int currentPosition = 0;

    public Belt(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.motor = motor;
    }
    public void runToPosition(int position) {
        motor.setTargetPosition(position);

        if(motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION)
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(RUN_TO_TARGET_POWER);
        //while(motor.isBusy());
    }
    public void setVelocity(int velocity)  {
        /*// Sings are reversed since motor is going in reverse
        if(motor.getCurrentPosition() < UPPER_LIMIT_POSITION - ENCODER_ERROR_OFFSET) {
            runToPosition(UPPER_LIMIT_POSITION + ENCODER_ERROR_OFFSET);
            return;
        } if(motor.getCurrentPosition() > LOWER_LIMIT_POSITION - ENCODER_ERROR_OFFSET) {
            runToPosition(LOWER_LIMIT_POSITION + ENCODER_ERROR_OFFSET);
            return;
        }
*/
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
        while(isBusy());
    }
    public void nextPosition() {
        currentPosition += 1;
        if(currentPosition == 3)
            currentPosition -= 3;

        bindToCurrentPosition();
    }
    public void prevPosition() {
        currentPosition -= 1;
        if(currentPosition == -1)
            currentPosition += 3;

        bindToCurrentPosition();
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    // Returns Belt to its start position which TeleOp sets up in the beginning
    public void reset() {
        runToPosition(LOWER_LIMIT_POSITION);
        while(isBusy());
    }

    public double getCurrentMotorPosition() {
        return motor.getCurrentPosition();
    }

}
