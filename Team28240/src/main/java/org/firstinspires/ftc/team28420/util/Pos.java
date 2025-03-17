package org.firstinspires.ftc.team28420.util;

public class Pos {
    public double x, y;
    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if (other == null || other.getClass() != this.getClass())
            return false;

        Pos pos = (Pos) other;
        return (this.x == pos.x) && (this.y == pos.y);
    }

}
