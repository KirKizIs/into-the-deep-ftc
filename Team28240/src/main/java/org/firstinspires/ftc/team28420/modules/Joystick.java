package org.firstinspires.ftc.team28420.modules;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.eventsystem.EventManager;
import org.firstinspires.ftc.team28420.eventsystem.EventType;
import org.firstinspires.ftc.team28420.eventsystem.EventValue;
import org.firstinspires.ftc.team28420.util.Pair;
import org.firstinspires.ftc.team28420.util.Pos;

import java.util.ArrayList;
import java.util.List;

public class Joystick{
    public Gamepad gamepad;
    public Gamepad prevGamepad = null;
    public EventManager events;

    public Joystick(Gamepad gamepad) {
        this.events = new EventManager();
        this.gamepad = gamepad;
    }

    public Pos getLeftStickPos() {
        return new Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public Pos getRightStickPos() {
        return new Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

    List<Pair<EventType, EventValue>> composeEventsList() {
        List<Pair<EventType, EventValue>> eventsList = new ArrayList<>();

        /* Sticks */
        if(gamepad.left_stick_x != prevGamepad.left_stick_x)
            eventsList.add(new Pair<>(EventType.LeftStickMoved,
                    new EventValue(gamepad.left_stick_x,gamepad.left_stick_y)));

        if(gamepad.right_stick_x != prevGamepad.right_stick_x)
            eventsList.add(new Pair<>(EventType.RightStickMoved,
                    new EventValue(gamepad.right_stick_x,gamepad.right_stick_y)));

        /* Figure Buttons */
        if(gamepad.triangle != prevGamepad.triangle)
            eventsList.add(new Pair<>(EventType.TrianglePressed,
                    new EventValue(
                            (gamepad.triangle?1:0)
                    )));
        if(gamepad.circle != prevGamepad.circle)
            eventsList.add(new Pair<>(EventType.CirclePressed,
                    new EventValue(
                            (gamepad.circle?1:0)
                    )));
        if(gamepad.cross != prevGamepad.cross)
            eventsList.add(new Pair<>(EventType.CrossPressed,
                    new EventValue(
                            (gamepad.cross?1:0)
                    )));
        if(gamepad.square != prevGamepad.square)
            eventsList.add(new Pair<>(EventType.SquarePressed,
                    new EventValue(
                            (gamepad.square?1:0)
                    )));

        /* Triggers */
        if(gamepad.left_trigger != prevGamepad.left_trigger)
            eventsList.add(new Pair<>(EventType.LeftTriggerMoved,
                    new EventValue(
                            gamepad.left_trigger
                    )));
        if(gamepad.right_trigger != prevGamepad.right_trigger)
            eventsList.add(new Pair<>(EventType.RightTriggerMoved,
                    new EventValue(
                            gamepad.right_trigger
                    )));

        /* Bumpers */
        if(gamepad.left_bumper != prevGamepad.left_bumper)
            eventsList.add(new Pair<>(EventType.LeftBumperPressed,
                    new EventValue(
                            (gamepad.left_bumper?1:0)
                    )));
        if(gamepad.right_bumper != prevGamepad.right_bumper)
            eventsList.add(new Pair<>(EventType.RightBumperPressed,
                    new EventValue(
                            (gamepad.right_bumper?1:0)
                    )));


        /* DPads */
        if(gamepad.dpad_up != prevGamepad.dpad_up)
            eventsList.add(new Pair<>(EventType.DPadUpPressed,
                    new EventValue(
                            (gamepad.dpad_up?1:0)
                    )));
        if(gamepad.dpad_down != prevGamepad.dpad_down)
            eventsList.add(new Pair<>(EventType.DPadUpPressed,
                    new EventValue(
                            (gamepad.dpad_up?1:0)
                    )));
        if(gamepad.dpad_left != prevGamepad.dpad_left)
            eventsList.add(new Pair<>(EventType.DPadLeftPressed,
                    new EventValue(
                            (gamepad.dpad_left?1:0)
                    )));
        if(gamepad.dpad_right != prevGamepad.dpad_right)
            eventsList.add(new Pair<>(EventType.DPadRightPressed,
                    new EventValue(
                            (gamepad.dpad_right?1:0)
                    )));
        return eventsList;
    }

    public void update() {
        List<Pair<EventType, EventValue>> pendingEvents = composeEventsList();
        for(Pair<EventType, EventValue> ev : pendingEvents)
            events.notifyEvent(ev.first, ev.second);
    }

}