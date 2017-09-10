package characters;

import util.Position;

/**
 * Created by ekaterina on 8/30/17.
 */
public class Granny {
    private static Position position = new Position();


    public static void setPosition(Position pos) {
        position = pos;
    }

    public static Position getPosition() {
        return position;
    }
}
