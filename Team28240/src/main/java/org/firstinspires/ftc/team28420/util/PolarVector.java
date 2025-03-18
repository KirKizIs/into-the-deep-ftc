package org.firstinspires.ftc.team28420.util;

public class PolarVector {
    public double theta, abs;
    public PolarVector(double theta, double abs) {
        this.theta = theta;
        this.abs = abs;
    }

    public static PolarVector getVectorFromPos(Pos pos) {
        return new PolarVector(Math.atan2(pos.y, pos.x), Math.hypot(pos.x, pos.y));
    }

}