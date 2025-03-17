package org.firstinspires.ftc.team28420.modules.bigkostyl;

//import org.firstinspires.ftc.team28420.EventSystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team28420.eventsystem.EventListener;
import org.firstinspires.ftc.team28420.eventsystem.EventType;
import org.firstinspires.ftc.team28420.eventsystem.EventValue;
import org.firstinspires.ftc.team28420.eventsystem.InvalidEventValueException;
import org.firstinspires.ftc.team28420.util.Pos;

public class BigKostyl implements EventListener {

    public static Telemetry telemetry = null;
    private Belt belt = null;
    private Claws claws = null;
    private Wrist wrist = null;

    public BigKostyl(DcMotor beltMotor, Servo wristServo, Servo clawsServo, Telemetry telemetry) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
    }

    void updateTelemetry() {
        telemetry.addData("Belt motor position: ", belt.getCurrentMotorPosition());
        telemetry.addData("Claws servo position: ", claws.getCurrentServoPosition());
        telemetry.addData("Wrist servo position: ", claws.getCurrentServoPosition());

        telemetry.update();
    }

    @Override
    public void update(EventType ev, EventValue value) {
        try {
            switch (ev) {
            case RightStickMoved:
                Pos rightStickValue = value.getStickValue();
                belt.setVelocity((int) (rightStickValue.y * Belt.TURN_VELOCITY));
                break;
            case TrianglePressed:
                wrist.turnUp();
                break;
            case CirclePressed:
                wrist.turnStraight();
                break;
            case CrossPressed:
                wrist.turnDown();
                break;
            case RightTriggerMoved:
                if(value.getValue() > 0)
                    claws.take(value.getValue());
                else claws.leave();
                break;
            }
        } catch(InvalidEventValueException e) {
            System.err.println("Unexpected EventValue type");
        }
    }

    public void handleJoystick(Gamepad gamepad) {
        /*belt.setVelocity((int)(gamepad.right_stick_y * Belt.TURN_VELOCITY));
        if(gamepad.square) {
            belt.setVelocity(0);
        }

        if(gamepad.triangle)
            wrist.turnUp();
        else if(gamepad.circle)
            wrist.turnStraight();
        else if(gamepad.cross)
            wrist.turnDown();

        if(gamepad.right_trigger > 0)
            claws.take(gamepad.right_trigger);
        else claws.leave();*/
    }
}
