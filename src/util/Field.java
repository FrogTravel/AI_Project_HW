package util;

import characters.Bear;
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
 * Field contain 2-dim array of Cells (see class Cell) that are containing
 * different characters.
 * You need to call newField and than generate Field to generate totally new field
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

        generateWolfPosition();
        generateBearPosition();

        generateWoodCutter();
        generateFalseWoodCutter();

        generateGrannyPosition();
    }


    /**
     * All cells on field are set to Free
     */
    private void initializeField() {
        for (int i = 0; i < field[0].length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new Cell();
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
        int x;
        int y;

        do {
            x = random.nextInt(Constants.FIELD_SIZE);
            y = random.nextInt(Constants.FIELD_SIZE);
        }while (!field[x][y].isFree());

        field[x][y].addStatus(Status.GRANNY);
        Granny.setPosition(new Position(x, y));
    }

    /**
     * Generation random position of wolf and its range
     */
    private void generateWolfPosition() {
        Wolf wolf = new Wolf();
        wolf.generatePosition(this);
        wolf.generateField(this);
    }

    /**
     * Generation random position of characters.Bear and its range
     */
    private void generateBearPosition(){
        Bear bear = new Bear();
        bear.generatePosition(this);
        bear.generateField(this);

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

    /**
     * Delete old position from field, and adds new position to new cell
     * @param oldPosition where RRH was
     * @param newPosition where she will be
     */
    public void changeRRHposition(Position oldPosition, Position newPosition) {
        try {
            field[oldPosition.getX()][oldPosition.getY()].deleteCharacter(Status.RRH);
            field[newPosition.getX()][newPosition.getY()].addStatus(Status.RRH);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Old Position: " + oldPosition.getX() + " " + oldPosition.getY());
            System.out.println("New Position: " + newPosition.getX() + " " + newPosition.getY());
        }
    }


    /**
     * Setters and getters
     */


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

    public Cell getGranniesCell() {
        return field[Granny.getPosition().getX()][Granny.getPosition().getY()];
    }

    /**
     * @return current field in form of string
     */
    public String getStringField() {
        String result = "";
        for (int i = 0; i < field.length; i++) {

            result += "\n";

            for (int j = 0; j < field.length; j++) {
                if(field[i][j].isFree())
                    result += " " + Constants.EMPTY_SYMBOL + "  ";
                else
                    result += field[i][j].getStatuses() + " ";
            }
            result += "\n";
        }
        for (int j = 0; j < field.length*4 + 1; j++) {
            result += "-";
        }
        result += "\n";
        return result;
    }

}
