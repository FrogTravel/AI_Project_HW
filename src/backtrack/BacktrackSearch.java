package backtrack;

import characters.Granny;
import characters.RRH;
import util.Constants;
import util.Field;
import util.HiddenField;
import util.Position;

import java.util.List;

/**
 * Created by ekaterina on 9/10/17.
 * Backtrack class
 */
public class BacktrackSearch {
    private RRH rrh;
    private int steps = 0;
    private HiddenField field;

    public BacktrackSearch(RRH rrh){
        this.rrh = rrh;
        field = rrh.getField();

        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if(field.getCell(i, j).isBear() || field.getCell(i, j).isWolf())
                    field.setClosed(i, j);
            }
        }
    }


    /**
     * Recursive Backtrack algorithm
     * Steps - number of steps (operations) already made by algorithm
     * if more than 100, break; RRH cannot make more than 81 step, but to be sure ^^
     *
     * if we reach Granny position, return true; Recursive breaks;
     * if we reach Wolf, return false;
     * if we reach Bear, return false;
     * Berries are substracted in RRH class
     *
     * For each way where she can go (i.e. UP, DOWN, RIGHT, LEFT)
     * If she can go there(i.e there is no wolf...) go there, and try to find solution
     * (call findPath(new list of positions) from this point
     * If no solution was found, delete all "new steps" and try to move to another direction
     *
     * In the case RRH got her self into corner, move backward, with already visited path
     *
     * @param testSolution partial Solution that we already have
     * @return true if solution was found, false if no solution was found
     */
    public boolean findPath(List<Position> testSolution){

        if(steps > 100){
            return false;
        }
        steps++;

        if(testSolution.get(testSolution.size() - 1).equals(Granny.getPosition())){
            return true;
        }else if(field.getCell(testSolution.get(testSolution.size() - 1)).isWolf()){
            return false;
        }else if(field.getCell(testSolution.get(testSolution.size() - 1)).isBear()){
            return false;
        }


        //For moving Up
        try {
            if (rrh.canGoUp() && !field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY())
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isBear()
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isWolf()) {
                rrh.goUp();

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
                if (findPath(testSolution)) {
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();
                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                        }catch (IndexOutOfBoundsException e){
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }


            }
        }catch (ArrayIndexOutOfBoundsException e){}

        //For moving Right
        try {
            if (rrh.canGoRight() && !field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() + 1)
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() + 1).isBear()
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() + 1).isWolf()) {
                rrh.goRight();

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

                if (findPath(testSolution)) {
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try{
                            testSolution.remove(i);
                        }catch (IndexOutOfBoundsException e){
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }

            }
        }catch (ArrayIndexOutOfBoundsException e){}


        //For moving Down
        try {
            if (rrh.canGoDown() && !field.isOpen(rrh.getPosition().getX() + 1, rrh.getPosition().getY())
                    && !field.getCell(rrh.getPosition().getX() + 1, rrh.getPosition().getY()).isBear()
                    && !field.getCell(rrh.getPosition().getX() + 1, rrh.getPosition().getY()).isWolf()) {
                rrh.goDown();

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

                if (findPath(testSolution)) {
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                        }catch (IndexOutOfBoundsException e){
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }

            }
        }catch (ArrayIndexOutOfBoundsException e){}


        //For moving Left
        try {
            if (rrh.canGoLeft() && !field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() - 1)
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() - 1).isBear()
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() - 1).isWolf()) {
                rrh.goLeft();

                int sizeOfOldSolution = testSolution.size();

                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

                if (findPath(testSolution)) {
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                        }catch (IndexOutOfBoundsException e){
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){}


        //If she got herself into corner
        if(isAllAroundVisited()){
            try {

                rrh.setPosition(new Position(testSolution.get(testSolution.size() - 2).getX(), testSolution.get(testSolution.size() - 2).getY()));
                testSolution.remove(testSolution.size() - 1);

                if (findPath(testSolution))
                    return true;

            }catch (IndexOutOfBoundsException e){
                return false;
            }
        }

        return false;
    }

    /**
     * Checks if all cells around her was already visited
     * @return true if was
     */
    public boolean isAllAroundVisited() {
        return field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() - 1) && field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() + 1)
            &&  field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY()) && field.isOpen(rrh.getPosition().getX() + 1, rrh.getPosition().getY());
    }

}
