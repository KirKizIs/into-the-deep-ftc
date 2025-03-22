package org.firstinspires.ftc.team28420.util.types;

public class Pos {
    public double x, y;
    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Pos multiply(double mult) {
        x *= mult;
        y *= mult;
        return this;
    }
}
