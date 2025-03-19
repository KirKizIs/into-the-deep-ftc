package org.firstinspires.ftc.team28420.modules;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team28420.eventsystem.EventManager;
import org.firstinspires.ftc.team28420.eventsystem.EventType;
import org.firstinspires.ftc.team28420.eventsystem.EventValue;
import org.firstinspires.ftc.team28420.eventsystem.JoystickStateChangeObserver;
import org.firstinspires.ftc.team28420.util.Pair;
import org.firstinspires.ftc.team28420.util.Pos;
import org.firstinspires.ftc.team28420.util.Vars;

import java.util.List;

public class Joystick{
    public Gamepad gamepad;
    public EventManager events;
    private final JoystickStateChangeObserver observer = new JoystickStateChangeObserver(this);

    public Joystick(Gamepad gamepad) {
        this.events = new EventManager();
        this.gamepad = gamepad;

    }

    public void copy(Joystick other) {
        this.gamepad = other.gamepad;

        this.gamepad.left_stick_y = other.gamepad.left_stick_y;
        this.gamepad.left_stick_x = other.gamepad.left_stick_x;
        this.gamepad.right_stick_y = other.gamepad.right_stick_y;
        this.gamepad.right_stick_x = other.gamepad.right_stick_x;

        this.gamepad.left_bumper = other.gamepad.left_bumper;
        this.gamepad.right_bumper = other.gamepad.right_bumper;

        this.gamepad.left_trigger = other.gamepad.left_trigger;
        this.gamepad.right_trigger = other.gamepad.right_trigger;

        this.gamepad.dpad_up = other.gamepad.dpad_up;
        this.gamepad.dpad_down = other.gamepad.dpad_down;
        this.gamepad.dpad_left = other.gamepad.dpad_left;
        this.gamepad.dpad_right = other.gamepad.dpad_right;

        this.gamepad.triangle = other.gamepad.triangle;
        this.gamepad.cross= other.gamepad.cross;
        this.gamepad.circle = other.gamepad.circle;
        this.gamepad.square = other.gamepad.square;

    }
    @Override
    public String toString() {
        if(gamepad == null) return "";
        StringBuilder sb = new StringBuilder();

        sb.append("LeftStick: (").append(gamepad.left_stick_x).append(" : ").append(gamepad.left_stick_y).append(")").append("\n");
        sb.append("RightStick: (").append(gamepad.right_stick_x).append(" : ").append(gamepad.right_stick_y).append(")").append("\n");

        sb.append("LeftBumper: ").append(this.gamepad.left_bumper).append("\n");
        sb.append("RightBumper: ").append(this.gamepad.right_bumper).append("\n");

        sb.append("LeftTrigger: ").append(this.gamepad.left_trigger).append("\n");
        sb.append("RightTrigger: ").append(this.gamepad.right_trigger).append("\n");

        sb.append("DPadUp: ").append(this.gamepad.dpad_up).append("\n");
        sb.append("DPadDown: ").append(this.gamepad.dpad_down).append("\n");
        sb.append("DPadLeft: ").append(this.gamepad.dpad_left).append("\n");
        sb.append("DPadRight: ").append(this.gamepad.dpad_right).append("\n");

        sb.append("Triangle: ").append(this.gamepad.triangle).append("\n");
        sb.append("Circle: ").append(this.gamepad.circle).append("\n");
        sb.append("Cross: ").append(this.gamepad.cross).append("\n");
        sb.append("Square: ").append(this.gamepad.square).append("\n");

        return sb.toString();
    }

    public Pos getLeftStickPos() {
        return new Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }

    public Pos getRightStickPos() {
        return new Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

    public boolean getTriangleButton() {
        return gamepad.triangle;
    }
    public boolean getSquareButton() {
        return gamepad.square;
    }
    public boolean getCircleButton() {
        return gamepad.circle;
    }
    public boolean getCrossButton() {
        return gamepad.cross;
    }

    public boolean getDPadUpButton() {
        return gamepad.dpad_up;
    }
    public boolean getDPadDownButton() {
        return gamepad.dpad_down;
    }
    public boolean getDPadLeftButton() {
        return gamepad.dpad_left;
    }
    public boolean getDPadRightButton() {
        return gamepad.dpad_right;
    }

    public boolean getRightBumperButton() {
        return gamepad.right_bumper;
    }
    public boolean getLeftBumperButton() {
        return gamepad.left_bumper;
    }

    public double getRightTrigger() {
        return gamepad.right_trigger;
    }
    public double getLeftTrigger() {
        return gamepad.left_trigger;
    }

    public Pos getLeftStick() {
        return new Pos(gamepad.left_stick_x, gamepad.left_stick_y);
    }
    public Pos getRightStick() {
        return new Pos(gamepad.right_stick_x, gamepad.right_stick_y);
    }

    public void update() {
        observer.update();

        List<Pair<EventType, EventValue>> pendingEvents = observer.composeEventsList();
        for(Pair<EventType, EventValue> ev : pendingEvents)
            events.notifyEvent(ev.first, ev.second);

    }

}