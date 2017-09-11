package util;

import util.Cell;
import util.Constants;
import util.Field;
import util.Position;

/**
 * For characters use, so they cannot have full access to all field
 */
public class HiddenField extends Field {
    private int[][] mask = new int[Constants.FIELD_SIZE][Constants.FIELD_SIZE];

    /**
     * Returns cell on current position
     * @param position position of that cell
     * @return empty cell if it is closed TODO maybe null (!Sic)
     */
    public Cell getCell(Position position){
        if(mask[position.getX()][position.getY()] == Constants.OPEN_CELL){
            return field[position.getX()][position.getY()];
        }
        return new Cell();
    }

    /**
     * Open cell on field
     * @param position where to open
     */
    public void addOpenCell(Position position) {
        mask[position.getX()][position.getY()] = Constants.OPEN_CELL;
    }

    /**
     * Overrided method
     * Displays only those cells that are already open
     */
    public void showField(){
        for (int i = 0; i < field.length; i++) {

            System.out.println();

            for (int j = 0; j < field.length; j++) {
//                if(field[i][j].isGranny()) {
//                    System.out.println(field[i][j].getStatuses() + "| ");
//                    break;
//                }
                if(mask[i][j] == Constants.CLOSED_CELL)
                    System.out.print(" " + Constants.CLOSED_SYMBOL + "  ");
                else if(field[i][j].isFree())
                    System.out.print(" " + Constants.EMPTY_SYMBOL + "  ");
                else
                    System.out.print(field[i][j].getStatuses() + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < field.length*4 + 1; j++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public boolean isOpen(int i, int y) {
        try {
            return mask[i][y] == Constants.OPEN_CELL;
        }catch (ArrayIndexOutOfBoundsException e){
            return true;
        }

    }

    public void setAllCellsClosed(){
        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                mask[i][j] = Constants.CLOSED_CELL;
            }

        }
    }

    public void setClosed(int i, int j) {
        mask[i][j] = Constants.OPEN_CELL;
    }
}
