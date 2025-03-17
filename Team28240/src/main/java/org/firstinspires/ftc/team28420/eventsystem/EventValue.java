package org.firstinspires.ftc.team28420.eventsystem;

import org.firstinspires.ftc.team28420.util.Pos;

public class EventValue {
    private double value;
    private Pos stickValue;

    boolean asPair = false;

    public EventValue(double value) {
        asPair = false;
        this.value = value;
    }

    public EventValue(double x, double y) {
        asPair = true;
        stickValue = new Pos(x,y);
    }

    public double getValue() throws InvalidEventValueException {
        if(asPair) throw new InvalidEventValueException();

        return value;
    }
    public Pos getStickValue() throws InvalidEventValueException{
        if(!asPair) throw new InvalidEventValueException();

        return stickValue;
    }
}
