package characters;

import util.Constants;
import util.Field;
import util.Position;
import util.Status;

import java.util.Random;

/**
 * Created by ekaterina on 9/3/17.
 * Wolf class that can generate position and detection range
 */
public class Wolf {
    private static Position position = new Position();

    /**
     * Generate new position of wolf
     * Request random int position in the size of field
     * Checks if this position is not blocking for RRH
     * And if it is free places bear there
     */
    public void generatePosition(Field field){
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while (field.getCell(x, y).isCharacter() || ((x == 0) && (y == 0))
                || ((x == 1) && (y == 0)) || ((x == 0) && (y == 1)));//If this cell doesn't contain character, then we can place wolf in it

        position = new Position(x, y);
        field.setCellStatus(position, Status.WOLF);
    }

    /**
     * Generates detection range around bear
     */
    public void generateField(Field field){
        int x = position.getX();
        int y = position.getY();
        if(x > 0)
            field.setCellStatus(x-1, y, Status.WOLF_RANGE);
        if(x < Constants.FIELD_SIZE - 1)
            field.setCellStatus(x+1, y, Status.WOLF_RANGE);
        if(y > 0)
            field.setCellStatus(x, y-1, Status.WOLF_RANGE);
        if(y < Constants.FIELD_SIZE - 1)
            field.setCellStatus(x, y+1, Status.WOLF_RANGE);
    }


    /**
     * Getters and setters
     */
    public static Position getPosition() {
        return position;
    }

    public static void setPosition(Position position) {
        Wolf.position = position;
    }
}
