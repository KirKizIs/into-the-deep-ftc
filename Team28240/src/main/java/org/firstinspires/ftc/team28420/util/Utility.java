package org.firstinspires.ftc.team28420.util;

import org.firstinspires.ftc.team28420.util.types.PolarVector;
import org.firstinspires.ftc.team28420.util.types.Pos;

public class Utility {

    public static PolarVector getVectorFromPos(Pos pos) {
        return new PolarVector(Math.atan2(pos.y, pos.x), Math.hypot(pos.x, pos.y));
    }

}
