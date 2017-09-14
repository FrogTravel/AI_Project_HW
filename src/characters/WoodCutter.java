package characters;

import util.Position;

import java.util.Random;

/**
 * Created by ekaterina on 8/30/17.
 * Wood cutter
 * He generates in the field class
 * If RRH comes to WC, and it is his real position, he gives her back all her berries.
 * It is handled in RRH class
 */
public class WoodCutter {
    private static Position realPosition;//Real position of woodcutter
    private static Position falsePosition;//False position of woodcutter

    /**
     * Getters and Setters
     */
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

    /**
     * @return array of random ordered position
     */
    public static Position[] getPositions() {
        Random random = new Random();

        if(random.nextBoolean()){
            return new Position[]{realPosition, falsePosition};
        }
        return new Position[]{falsePosition, realPosition};
    }
}
