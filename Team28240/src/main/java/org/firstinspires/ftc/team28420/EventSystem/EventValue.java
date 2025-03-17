package org.firstinspires.ftc.team28420.EventSystem;

import org.firstinspires.ftc.team28420.Util.Vector2d;

public class EventValue {
    private double value;
    private Vector2d stickValue;

    boolean isPair = false;

    EventValue(double value) {
        isPair = false;
        this.value = value;
    }

    EventValue(double x, double y) {
        isPair = true;
        stickValue = new Vector2d(x,y);
    }

    public double getValue() throws InvalidEventValueException {
        if(isPair) throw new InvalidEventValueException();

        return value;
    }
    public Vector2d getStickValue() throws InvalidEventValueException{
        if(!isPair) throw new InvalidEventValueException();

        return stickValue;
    }
}
