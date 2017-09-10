package util;

import astar.Node;
import characters.Granny;
import characters.Wolf;
import characters.WoodCutter;
import util.Cell;
import util.Constants;
import util.Position;
import util.Status;

import java.util.Random;

/**
 * Created by ekaterina on 8/30/17.
 */
public class Field {
    protected static Cell[][] field;

    public void newField(){
        field = new Cell[Constants.FIELD_SIZE][Constants.FIELD_SIZE];
        initializeField();
    }

    /**
     * Generate new random field
     */
    public void generateField(){
        initializeField();
        generateGrannyPosition();

        generateWolfPosition();
        generateBearPosition();

        generateWoodCutter();
        generateFalseWoodCutter();
    }


    /**
     * All cells on field are set to Free
     */
    private void initializeField() {
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new Cell();
                //field[i][j].setFree();
            }
        }
    }

    /**
     * Print field
     */
    public void showField(){
        for (int i = 0; i < field.length; i++) {

            System.out.println();

            for (int j = 0; j < field.length; j++) {
                if(field[i][j].isFree())
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


    /**
     * Generation random position of characters.Granny
     */
    private void generateGrannyPosition(){
        Random random = new Random();
        int x = random.nextInt(Constants.FIELD_SIZE);
        int y = random.nextInt(Constants.FIELD_SIZE);

        field[x][y].addStatus(Status.GRANNY);
        Granny.setPosition(new Position(x, y));
    }

    /**
     * Generation random position of wolf and its range
     */
    private void generateWolfPosition() {
//        Random random = new Random();
//        int x;
//        int y;
//
//        do {
//            x = random.nextInt(util.Constants.FIELD_SIZE);
//            y = random.nextInt(util.Constants.FIELD_SIZE);
//        } while (field[x][y].isCharacter());//If this cell doesn't contain character, then we can place wolf in it
//
//        field[x][y].addStatus(util.Status.WOLF);
//        if(x > 0)
//            field[x-1][y].addStatus(util.Status.WOLF_RANGE);
//        if(x < util.Constants.FIELD_SIZE - 1)
//            field[x+1][y].addStatus(util.Status.WOLF_RANGE);
//        if(y > 0)
//            field[x][y-1].addStatus(util.Status.WOLF_RANGE);
//        if(y < util.Constants.FIELD_SIZE - 1)
//            field[x][y+1].addStatus(util.Status.WOLF_RANGE);

        Wolf wolf = new Wolf();
        wolf.generatePosition(this);
        wolf.generateField(this);
    }

    /**
     * Generation random position of characters.Bear and its range
     */
    private void generateBearPosition(){
        Random random = new Random();
        int x;
        int y;

        do{
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while((field[x][y].isCharacter()) || ((x == 0) && (y == 1))
                || ((x == 1) && (y == 0)) || ((x == 0) && (y == 0)));

        field[x][y].addStatus(Status.BEAR);
        if(x > 0)
            field[x - 1][y].addStatus(Status.BEAR_RANGE);


        if(x < Constants.FIELD_SIZE - 1)
            field[x + 1][y].addStatus(Status.BEAR_RANGE);


        if(y > 0)
            field[x][y - 1].addStatus(Status.BEAR_RANGE);


        if(y < Constants.FIELD_SIZE - 1)
            field[x][y + 1].addStatus(Status.BEAR_RANGE);

        if((x > 0) && (y > 0))
            field[x - 1][y - 1].addStatus(Status.BEAR_RANGE);
        if((x > 0) && (y < Constants.FIELD_SIZE - 1))
            field[x - 1][y + 1].addStatus(Status.BEAR_RANGE);
        if((x < Constants.FIELD_SIZE - 1) && (y > 0))
            field[x + 1][y - 1].addStatus(Status.BEAR_RANGE);
        if((x < Constants.FIELD_SIZE - 1) && (y < Constants.FIELD_SIZE - 1))
            field[x + 1][y + 1].addStatus(Status.BEAR_RANGE);
    }

    /**
     * Generates wood cutter position
     */
    private void generateWoodCutter(){
        Random random = new Random();
        int x;
        int y;

        do{
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while (!field[x][y].isFree());


        field[x][y].addStatus(Status.WOOD_CUTTER);

        WoodCutter.setRealPosition(new Position(x, y));
    }

    /**
     * Generates false wood cutter position
     */
    private void generateFalseWoodCutter(){
        Random random = new Random();
        int x;
        int y;

        do{
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        } while (!field[x][y].isFree());

        field[x][y].addStatus(Status.FALSE_WOOD_CUTTER);

        WoodCutter.setFalsePosition(new Position(x, y));
    }

    public void setGrannyPosition(Position position){
        field[position.getX()][position.getY()].addStatus(Status.GRANNY);
    }

    public void setRealWoodCutterPosition(Position position){
        field[position.getX()][position.getY()].addStatus(Status.WOOD_CUTTER);
    }

    public void setFalseWoodCutterPosition(Position position){
        field[position.getX()][position.getY()].addStatus(Status.FALSE_WOOD_CUTTER);
    }

    public void setRRHPosition(Position position){
        field[position.getX()][position.getY()].addStatus(Status.RRH);
    }

    public Cell getCell(Position position){
        return field[position.getX()][position.getY()];
    }

    public Cell getCell(int x, int y){
        return field[x][y];
    }

    public void setCellStatus(Position position, int status) {
        field[position.getX()][position.getY()].addStatus(status);
    }

    public void setCellStatus(int x, int y, int status) {
        field[x][y].addStatus(status);
    }

    public void changeRRHposition(Position oldPosition, Position newPosition) {
        try {
            field[oldPosition.getX()][oldPosition.getY()].deleteCharacter(Status.RRH);
            field[newPosition.getX()][newPosition.getY()].addStatus(Status.RRH);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Old Position: " + oldPosition.getX() + " " + oldPosition.getY());
            System.out.println("New Position: " + newPosition.getX() + " " + newPosition.getY());
        }
    }

    public Cell getGranniesCell() {
        return field[Granny.getPosition().getX()][Granny.getPosition().getY()];
    }

    public void setClosed(int i, int j) {
        System.out.println("Can't close");
    }
}
