package characters;

import util.Constants;
import util.Field;
import util.Position;
import util.Status;

import java.util.Random;

/**
 * Created by ekaterina on 9/4/17.
 * Bear class that can generate position and generate field around it
 */
public class Bear {
    private static Position position = new Position();

    /**
     * Generate new position of bear
     * Request random int position in the size of field
     * Checks if this position is not blocking for RRH
     * And if it is free places bear there
     */
    public void generatePosition(Field field){
        Random random = new Random();
        int x;
        int y;

        do{
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while((field.getCell(x,y).isCharacter()) || ((x == 0) && (y == 1))
                || ((x == 1) && (y == 0)) || ((x == 0) && (y == 0))
                || ((x== 1) && (y == 1)));

        position = new Position(x, y);
        field.getCell(x,y).addStatus(Status.BEAR);
    }

    /**
     * Generates detection range around bear
     */
    public void generateField(Field field){
        int x = position.getX();
        int y = position.getY();
        if(x > 0)
            field.getCell(x-1, y).addStatus(Status.BEAR_RANGE);


        if(x < Constants.FIELD_SIZE - 1)
            field.getCell(x+1, y).addStatus(Status.BEAR_RANGE);


        if(y > 0)
            field.getCell(x, y-1).addStatus(Status.BEAR_RANGE);


        if(y < Constants.FIELD_SIZE - 1)
            field.getCell(x, y+1).addStatus(Status.BEAR_RANGE);

        if((x > 0) && (y > 0))
            field.getCell(x-1, y-1).addStatus(Status.BEAR_RANGE);
        if((x > 0) && (y < Constants.FIELD_SIZE - 1))
            field.getCell(x-1, y+1).addStatus(Status.BEAR_RANGE);
        if((x < Constants.FIELD_SIZE - 1) && (y > 0))
            field.getCell(x+1, y-1).addStatus(Status.BEAR_RANGE);
        if((x < Constants.FIELD_SIZE - 1) && (y < Constants.FIELD_SIZE - 1))
            field.getCell(x+1, y+1).addStatus(Status.BEAR_RANGE);
    }

    /**
     * Getters and setters
     */

    public static Position getPosition() {
        return position;
    }

    public static void setPosition(Position position) {
        Bear.position = position;
    }
}
