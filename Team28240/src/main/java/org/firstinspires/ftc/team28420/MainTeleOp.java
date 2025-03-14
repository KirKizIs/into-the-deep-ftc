package org.firstinspires.ftc.team28420;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team28420.BigKostyl.BigKostyl;

@TeleOp(name = "main teleop", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {
    public static Telemetry telem;

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();

        while(opModeIsActive()) {
            handleJoystick();
        }
    }

    private void initHardware() {

    }

    private void handleJoystick() {
    }

}
