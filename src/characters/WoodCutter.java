package characters;

import util.Position;

import java.util.Random;

/**
 * Created by ekaterina on 8/30/17.
 */
public class WoodCutter {
    private static Position realPosition;
    private static Position falsePosition;

    public static Position getRealPosition() {
        return realPosition;
    }

    public static void setRealPosition(Position realPosition) {
        WoodCutter.realPosition = realPosition;
    }

    public static Position getFalsePosition() {
        return falsePosition;
    }

    public static void setFalsePosition(Position falsePosition) {
        WoodCutter.falsePosition = falsePosition;
    }

    public static Position[] getPositions() {
        Random random = new Random();


        if(random.nextBoolean()){
            return new Position[]{realPosition, falsePosition};
        }
        return new Position[]{falsePosition, realPosition};
    }
}
