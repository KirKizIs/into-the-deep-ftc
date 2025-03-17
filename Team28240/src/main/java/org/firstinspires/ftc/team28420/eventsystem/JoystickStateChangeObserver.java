package org.firstinspires.ftc.team28420.eventsystem;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.modules.Joystick;
import org.firstinspires.ftc.team28420.util.Pair;

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
        if(current.getLeftStick().equals(previous.getLeftStick()))
            eventsList.add(new Pair<>(EventType.LeftStickMoved,
                    new EventValue(current.getLeftStick().x, current.getLeftStick().y)));

        if(current.getRightStick().equals(previous.getRightStick()))
            eventsList.add(new Pair<>(EventType.RightStickMoved,
                    new EventValue(current.getRightStick().x, current.getRightStick().y)));


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
        return eventsList;
    }

    public void update() {
        if(previous == null)
            previous = new Joystick(new Gamepad());
        previous.copy(current);
    }

}
