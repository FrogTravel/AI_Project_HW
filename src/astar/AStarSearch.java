package astar;

import characters.Granny;
import characters.RRH;
import characters.WoodCutter;
import util.Cell;
import util.Constants;
import util.Field;
import util.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ekaterina on 9/9/17.
 */
public class AStarSearch {
    private RRH rrh;
    private StarNode[][] field;
    private int steps;

    public AStarSearch(RRH rrh) {
        field = new StarNode[Constants.FIELD_SIZE][Constants.FIELD_SIZE];
        this.rrh = rrh;

        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                field[i][j] = new StarNode();
                field[i][j].setPosition(i,j);
            }
        }

    }

    public List<Position> startSearch(){
        steps = 0;
        findPath();

        List<Position> positions = new LinkedList<>();
        positions.add(new Position(0,0));

        StarNode node = field[Granny.getPosition().getX()][Granny.getPosition().getY()];

        int i = 0;

        while (node.getParent() != null){
            if(i > 82)
                break;
            i++;
            positions.add(node.getPosition());
            node = node.getParent();
        }
        return positions;
    }

    private void findPath(){
        field = new StarNode[Constants.FIELD_SIZE][Constants.FIELD_SIZE];

        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                field[i][j] = new StarNode();
                field[i][j].setPosition(i,j);
            }
        }

        StarNode current = field[0][0];//position of rrh
        current.setPosition(0,0);
        current.setCosts(0,0);
        current.setOpen(true);

        StarNode leftNeighbour = null, upNeighbour = null, rightNeighbour = null, downNeighbour = null;
        StarNode goal = field[Granny.getPosition().getX()][Granny.getPosition().getY()];
        goal.setPosition(Granny.getPosition().getX(), Granny.getPosition().getY());

        List<StarNode> opened = new ArrayList<>();
        opened.add(current);

        Field rrhField = rrh.getField();
        while(true) {


            steps++;
            if(steps > 100) {//something went wrong
                rrh.getField().showField();
                break;
            }

            current = findLowestFCost(opened);

            if((current.getPosition().getX() == -1) && (current.getPosition().getY() == -1)) {
                System.out.println("No solution!");
                break;
            }

            opened.remove(current);

            rrh.setPosition(current.getPosition());//Change position of rrh to current cell

            Cell currentCell = rrhField.getCell(current.getPosition().getX(), current.getPosition().getY());//Get current cell

            current.setTraversable(false);//TODO not sure this is right

            //printField(current.getPosition());

            //If we have reach the goal
            if((current.getPosition().getX() == goal.getPosition().getX()) && (current.getPosition().getY() == goal.getPosition().getY())){
                return;
            }

            //If we can get left neighbour (not out of bound exception)
            try {
                leftNeighbour = field[current.getPosition().getX() - 1][current.getPosition().getY()];
                leftNeighbour.setPosition(current.getPosition().getX() - 1, current.getPosition().getY());
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            //If we can get up neighbour (not out of bound exception)
            try {
                upNeighbour = field[current.getPosition().getX()][current.getPosition().getY() - 1];
                upNeighbour.setPosition(current.getPosition().getX(), current.getPosition().getY() - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            //If we can get right neighbour (not out of bound exception)
            try {
                rightNeighbour = field[current.getPosition().getX() + 1][current.getPosition().getY()];
                rightNeighbour.setPosition(current.getPosition().getX() + 1, current.getPosition().getY());
            } catch (ArrayIndexOutOfBoundsException e) {
            }


            //If we can get down neighbour (not out of bound exception)
            try {
                downNeighbour = field[current.getPosition().getX()][current.getPosition().getY() + 1];
                downNeighbour.setPosition(current.getPosition().getX(), current.getPosition().getY() + 1);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            Cell upCell = null, downCell = null, leftCell = null, rightCell = null;

            //Get neighbour cells (if can not OutOfBoundException)
            try {
                upCell = rrhField.getCell(current.getPosition().getX(), current.getPosition().getY() - 1);
            }catch (ArrayIndexOutOfBoundsException e){}

            try {
                downCell = rrhField.getCell(current.getPosition().getX(), current.getPosition().getY() + 1);
            }catch (ArrayIndexOutOfBoundsException e){}

            try {
                leftCell = rrhField.getCell(current.getPosition().getX() - 1, current.getPosition().getY());
            }catch (ArrayIndexOutOfBoundsException e){}

            try {
                rightCell = rrhField.getCell(current.getPosition().getX() + 1, current.getPosition().getY());
            }catch (ArrayIndexOutOfBoundsException e){}

            if((upCell != null) && ((upCell.isWolf()) || (upCell.isBear()))&& (upNeighbour != null)) {
                upNeighbour.setTraversable(false);
            }
            if((downCell != null) && ((downCell.isWolf()) || (downCell.isBear())) && (downNeighbour != null)) {
                downNeighbour.setTraversable(false);
            }
            if((leftCell != null) && ((leftCell.isWolf()) || (leftCell.isBear())) && (leftNeighbour != null)) {
                leftNeighbour.setTraversable(false);
            }
            if((rightCell != null) && ((rightCell.isWolf()) || (rightCell.isBear())) && (rightNeighbour != null)) {
                rightNeighbour.setTraversable(false);
            }

            if ((downNeighbour != null) && (downNeighbour.isTraversable()) && ((!downNeighbour.isOpen()) || (current.getGCost() > countGCost(current, downNeighbour)))) {//if neighbour cell is closed(not evaluated) or
                downNeighbour.setCosts(countGCost(current, downNeighbour), countHCost(downNeighbour, goal));
                downNeighbour.setOpen(true);
                downNeighbour.setParent(current);
                opened.add(downNeighbour);
            }
            if ((rightNeighbour != null) && (rightNeighbour.isTraversable()) && ((!rightNeighbour.isOpen()) || (current.getGCost() > countGCost(current, rightNeighbour)))) {//if neighbour cell is closed(not evaluated) or
                rightNeighbour.setCosts(countGCost(current, rightNeighbour), countHCost(rightNeighbour, goal));
                rightNeighbour.setOpen(true);
                rightNeighbour.setParent(current);
                opened.add(rightNeighbour);
            }
            if ((leftNeighbour != null) && (leftNeighbour.isTraversable()) && ((!leftNeighbour.isOpen()) || (current.getGCost() > countGCost(current, leftNeighbour)))) {//if neighbour cell is closed(not evaluated) or
                leftNeighbour.setCosts(countGCost(current, leftNeighbour), countHCost(leftNeighbour, goal));
                leftNeighbour.setOpen(true);
                leftNeighbour.setParent(current);
                opened.add(leftNeighbour);
            }
            if ((upNeighbour != null) && (upNeighbour.isTraversable()) && ((!upNeighbour.isOpen()) || (current.getGCost() > countGCost(current, upNeighbour)))) {//if neighbour cell is closed(not evaluated) or
                upNeighbour.setCosts(countGCost(current, upNeighbour), countHCost(upNeighbour, goal));
                upNeighbour.setOpen(true);
                upNeighbour.setParent(current);
                opened.add(upNeighbour);
            }


        }
    }




    private StarNode findLowestFCost(List<StarNode> opened) {
        StarNode min = new StarNode();
        min.setFCost(1000000);
        for (StarNode node : opened) {
            if(min.getFCost() > node.getFCost() && (node.getFCost() != -1)){
                min = node;
            }
        }
        return min;
    }

    private int countGCost(StarNode current, StarNode neighbour) {
        return (Math.abs(current.getPosition().getX() - neighbour.getPosition().getX()) + Math.abs(current.getPosition().getY() - neighbour.getPosition().getY())) * 10;
    }

    private int countHCost(StarNode current, StarNode goal) {
        return (Math.abs(current.getPosition().getX() - goal.getPosition().getX()) + Math.abs(current.getPosition().getY() - goal.getPosition().getY())) * 10;
    }

    private void printField(Position current){
        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if((i == current.getX()) && (j == current.getY()))
                    System.out.print("[c]");
                System.out.print(field[i][j].getFCost() + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}
