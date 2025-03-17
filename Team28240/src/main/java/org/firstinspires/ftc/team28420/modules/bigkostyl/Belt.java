package org.firstinspires.ftc.team28420.modules.bigkostyl;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

class Belt {
    public DcMotor motor = null;

    public static final int TURN_VELOCITY = 1000;

    public Belt(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.motor = motor;
    }

    public void setVelocity(int velocity) {
        ((DcMotorEx) motor ).setVelocity(velocity);
    }

    // Returns Belt to its start position which TeleOp sets up in the beginning
    public void reset() {
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(1);
        while(motor.isBusy());

        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double getCurrentMotorPosition() {
        return motor.getCurrentPosition();
    }

}
