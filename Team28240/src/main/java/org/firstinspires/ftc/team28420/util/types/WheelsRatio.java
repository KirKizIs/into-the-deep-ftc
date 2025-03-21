package org.firstinspires.ftc.team28420.util.types;

public class WheelsRatio {

    public double leftFront, rightFront, leftBack, rightBack;

    public WheelsRatio(double leftFront, double rightFront, double leftBack, double rightBack) {
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
    }

    public static final WheelsRatio ZERO = new WheelsRatio(0, 0, 0, 0);

    public WheelsRatio multiply(double k) {
        leftFront *= k;
        rightFront *= k;
        leftBack *= k;
        rightBack *= k;

        return this;
    }

    @Override
    public String toString() {
        return "WheelsRatio{" +
                "leftFront=" + leftFront +
                ", rightFront=" + rightFront +
                ", leftBack=" + leftBack +
                ", rightBack=" + rightBack +
                '}';
    }

}
