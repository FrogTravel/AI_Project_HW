package characters;

import util.Constants;
import util.Field;
import util.Position;
import util.Status;

import java.util.Random;

/**
 * Created by ekaterina on 8/30/17.
 * Granny agent
 * She can generate position
 * If RRH will come to her, RRH will win. It is handled in RRH class
 */
public class Granny {
    private static Position position = new Position();

    /**
     * Generate new position of granny
     * Request random int position in the size of field
     * Checks if this position is not blocking for RRH
     * And if it is free places granny there
     */
    public void generatePosition(Field field) {
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while (!field.getCell(x, y).isFree());

        field.getCell(x, y).addStatus(Status.GRANNY);
        position = new Position(x, y);
    }

    public static void setPosition(Position pos) {
        position = pos;
    }

    public static Position getPosition() {
        return position;
    }
}
