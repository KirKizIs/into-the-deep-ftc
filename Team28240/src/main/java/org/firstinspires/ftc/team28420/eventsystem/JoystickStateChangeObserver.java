package org.firstinspires.ftc.team28420.eventsystem;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.modules.Joystick;
import org.firstinspires.ftc.team28420.util.Pair;
import org.firstinspires.ftc.team28420.util.Vars;

import java.util.ArrayList;
import java.util.List;

public class JoystickStateChangeObserver {
    public Joystick current;
    public Joystick previous = null;

    public JoystickStateChangeObserver(Joystick joystick) {
        current = joystick;
    }

    public List<Pair<EventType, EventValue>> composeEventsList() {
        List<Pair<EventType, EventValue>> eventsList = new ArrayList<>();
        if(previous == null) return eventsList;

        /* Sticks */
        if(!current.getLeftStick().equals(previous.getLeftStick()))
            eventsList.add(new Pair<>(EventType.LeftStickMoved,
                    new EventValue(
                            (Math.abs(current.getLeftStick().x)>0.2) ? current.getLeftStick().x : 0,
                            (Math.abs(current.getLeftStick().y)>0.2) ? current.getLeftStick().y : 0
                    )));

        if(!current.getRightStick().equals(previous.getRightStick()))
            eventsList.add(new Pair<>(EventType.RightStickMoved,
                    new EventValue(
                            (Math.abs(current.getRightStick().x)>0.2) ? current.getRightStick().x : 0,
                            (Math.abs(current.getRightStick().y)>0.2) ? current.getRightStick().y : 0
                    )));


        /* Figure Buttons */
        if(current.getTriangleButton() != previous.getTriangleButton())
            eventsList.add(new Pair<>(EventType.TrianglePressed,
                    new EventValue(
                            (current.getTriangleButton()?1:0)
                    )));
        if(current.getCircleButton() != previous.getCircleButton())
            eventsList.add(new Pair<>(EventType.CirclePressed,
                    new EventValue(
                            (current.getCircleButton()?1:0)
                    )));
        if(current.getCrossButton() != previous.getCrossButton())
            eventsList.add(new Pair<>(EventType.CrossPressed,
                    new EventValue(
                            (current.getCrossButton()?1:0)
                    )));
        if(current.getSquareButton() != previous.getSquareButton())
            eventsList.add(new Pair<>(EventType.SquarePressed,
                    new EventValue(
                            (current.getSquareButton()?1:0)
                    )));

        /* Triggers */
        if(current.getLeftTrigger() !=  previous.getLeftTrigger())
            eventsList.add(new Pair<>(EventType.LeftTriggerMoved,
                    new EventValue(
                            current.getLeftTrigger()
                    )));
        if(current.getRightTrigger() !=  previous.getRightTrigger())
            eventsList.add(new Pair<>(EventType.RightTriggerMoved,
                    new EventValue(
                            current.getRightTrigger()
                    )));

        /* Bumpers */
        if(current.getLeftBumperButton() !=  previous.getLeftBumperButton())
            eventsList.add(new Pair<>(EventType.LeftBumperPressed,
                    new EventValue(
                            (current.getLeftBumperButton()?1:0)
                    )));
        if(current.getRightBumperButton() !=  previous.getRightBumperButton())
            eventsList.add(new Pair<>(EventType.RightBumperPressed,
                    new EventValue(
                            (current.getRightBumperButton()?1:0)
                    )));


        /* DPads */
        if(current.getDPadUpButton() !=  previous.getDPadUpButton())
            eventsList.add(new Pair<>(EventType.DPadUpPressed,
                    new EventValue(
                            (current.getDPadUpButton()?1:0)
                    )));
        if(current.getDPadDownButton() !=  previous.getDPadDownButton())
            eventsList.add(new Pair<>(EventType.DPadUpPressed,
                    new EventValue(
                            (current.getDPadDownButton()?1:0)
                    )));
        if(current.getDPadLeftButton() !=  previous.getDPadLeftButton())
            eventsList.add(new Pair<>(EventType.DPadLeftPressed,
                    new EventValue(
                            (current.getDPadLeftButton()?1:0)
                    )));
        if(current.getDPadRightButton() !=  previous.getDPadRightButton())
            eventsList.add(new Pair<>(EventType.DPadRightPressed,
                    new EventValue(
                            (current.getDPadRightButton()?1:0)
                    )));
        for(Pair<EventType, EventValue> ev : eventsList)
            Vars.telemetry.addData("event ( ", ev.first + " : " + ev.second);

        if(!eventsList.isEmpty()) previous.copy(current);

        return eventsList;
    }
    public void update() {
        if(previous == null)
            previous = new Joystick(new Gamepad());

        Vars.telemetry.addData("", current.toString());
        Vars.telemetry.addData("", previous.toString());


        //previous.copy(current);
    }

}
