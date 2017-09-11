package characters;

import util.Constants;
import util.HiddenField;
import util.Position;
import util.Status;

/**
 * Created by ekaterina on 8/30/17.
 */
public class RRH {
    private Position grannyPosition;
    private Position[] woodCutterPosition;
    private Position realWoodCutterPosition;
    private HiddenField field;

    private int berries = 6;
    private Position position;

    public RRH() {
        field = new HiddenField();
        grannyPosition = Granny.getPosition();
        woodCutterPosition = WoodCutter.getPositions();

        position = new Position(0, 0);
        field.setRRHPosition(position);
        field.addOpenCell(position);
        checkCurrentCell();
    }

    public HiddenField getField() {
        return field;
    }

    /**
     * She change her position to one cell Down,
     * checks her current cell
     */
    public void goDown() {
        Position oldPosition = new Position(position.getX(), position.getY());
        position.change(position.getX() + 1, position.getY());
        field.addOpenCell(position);
        field.changeRRHposition(oldPosition, position);
        checkCurrentCell();
    }

    /**
     * She change her position to one cell Up,
     * checks her current cell
     */
    public void goUp() {
        Position oldPosition = new Position(position.getX(), position.getY());
        position.change(position.getX() - 1, position.getY());
        field.addOpenCell(position);
        field.changeRRHposition(oldPosition, position);
        checkCurrentCell();
    }

    /**
     * She change her position to one cell Right,
     * checks her current cell
     */
    public void goRight()  {
        Position oldPosition = new Position(position.getX(), position.getY());
        position.change(position.getX(), position.getY() + 1);
        field.addOpenCell(position);
        field.changeRRHposition(oldPosition, position);
        checkCurrentCell();
    }

    /**
     * She change her position to one cell Left,
     * checks her current cell
     */
    public void goLeft()  {
        Position oldPosition = new Position(position.getX(), position.getY());
        position.change(position.getX(), position.getY() - 1);
        field.addOpenCell(position);
        field.changeRRHposition(oldPosition, position);
        checkCurrentCell();
    }

    /**
     * Checks if current cell s busy with someone and acts
     */
    private void checkCurrentCell()  {
        if(field.getCell(position).getStatuses().contains(Status.WOLF_RANGE))//TODO make more beautiful
           // throw new util.exceptions.GameOverException();
        if(field.getCell(position).getStatuses().contains(Status.BEAR_RANGE)) {
            berries -= 2;
        }
        if(field.getCell(position).getStatuses().contains(Status.WOOD_CUTTER)) {
            realWoodCutterPosition = position;
            berries = 6;
        }
        if((field.getCell(position).getStatuses().contains(Status.GRANNY)) && (berries == 6));
            //throw new util.exceptions.GameWinException();

        if (berries == 0);
           // throw new util.exceptions.GameOverException();
    }

    public int getBerries() {
        return berries;
    }

    public Position getPosition() {
        return position;
    }

    public boolean canGoUp() {
        return (position.getX() - 1 >= 0);
    }

    public boolean canGoRight() {
        return (position.getY() + 1 < Constants.FIELD_SIZE);
    }

    public boolean canGoLeft(){
        return (position.getY() - 1 >= 0);
    }

    public boolean canGoDown(){
        return (position.getX() + 1 < Constants.FIELD_SIZE);
    }

    public void setPosition(Position position) {
        Position oldPosition = this.position;
        this.position = position;

        field.changeRRHposition(oldPosition,position);
        field.addOpenCell(position);
        checkCurrentCell();
    }
}
