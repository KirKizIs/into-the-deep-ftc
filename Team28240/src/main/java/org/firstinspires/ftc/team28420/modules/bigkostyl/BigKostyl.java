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

    public BigKostyl(DcMotor beltMotor, Servo wristServo, Servo clawsServo, Telemetry telem) {
        belt = new Belt(beltMotor);
        wrist = new Wrist(wristServo);
        claws = new Claws(clawsServo);
        BigKostyl.telemetry = telem;
    }

    public void updateTelemetry() {
        telemetry.addData("Belt motor position: ", belt.getCurrentMotorPosition());
        telemetry.addData("Wrist servo position: ", wrist.getCurrentServoPosition());
        telemetry.addData("Claws servo position: ", claws.getCurrentServoPosition());

        telemetry.update();
    }

    @Override
    public void update(EventType ev, EventValue value) {
        try {
            if(ev == EventType.RightStickMoved) {
                Pos rightStickValue = value.getStickValue();
                belt.setVelocity((int) (-rightStickValue.y * Belt.TURN_VELOCITY));
            } else if(ev == EventType.TrianglePressed)
                wrist.turnUp();
            else if(ev == EventType.CirclePressed)
                wrist.turnStraight();
            else if(ev == EventType.CrossPressed)
                wrist.turnDown();
            else if(ev == EventType.RightTriggerMoved)
                if(value.getValue() > 0)
                    claws.take(value.getValue());
                else claws.leave();
        } catch(InvalidEventValueException e) {
            System.err.println("Unexpected EventValue type");
        }
    }
}
