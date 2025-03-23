package org.firstinspires.ftc.team28420.util.types;

public class PolarVector {
    public double theta, abs;
    public PolarVector(double theta, double abs) {
        this.theta = theta;
        this.abs = abs;
    }
    public PolarVector rotate(double angle) {
        theta += angle;
        theta %= Math.PI * 2;
        return this;
    }
}
