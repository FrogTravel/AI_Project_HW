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

    public boolean findPath(List<Position> testSolution){
        rrh.getField().showField();

        if(steps > 100){
            System.out.println("YOU ARE LOOSER!!");
            return false;
        }
        steps++;

        if(testSolution.get(testSolution.size() - 1).equals(Granny.getPosition())){
            System.out.println("GRANNY");
            return true;
        }else if(field.getCell(testSolution.get(testSolution.size() - 1)).isWolf()){
            System.out.println("WOLF");
            return false;
        }else if(field.getCell(testSolution.get(testSolution.size() - 1)).isBear()){
            System.out.println("BEAR");
            return false;
        }


        try {
            if (rrh.canGoUp() && !field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY())
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isBear()
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isWolf()) {
                rrh.goUp();
                System.out.println("GO UP");
                printTestSolution(testSolution);

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
                if (findPath(testSolution)) {
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();
                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                            System.out.println("Has removed something");
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("INDEX OUT OF BOUND: goUp");
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }


            }
        }catch (ArrayIndexOutOfBoundsException e){}

        try {
            if (rrh.canGoRight() && !field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() + 1)
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() + 1).isBear()
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() + 1).isWolf()) {
                rrh.goRight();
                System.out.println("GO RIGHT");
                printTestSolution(testSolution);

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
                if (findPath(testSolution)) {
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try{
                            testSolution.remove(i);
                            System.out.println("Has removed something");
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("INDEX OUT OF BOUND: goRight");
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }

            }
        }catch (ArrayIndexOutOfBoundsException e){}

        try {
            if (rrh.canGoDown() && !field.isOpen(rrh.getPosition().getX() + 1, rrh.getPosition().getY())
                    && !field.getCell(rrh.getPosition().getX() + 1, rrh.getPosition().getY()).isBear()
                    && !field.getCell(rrh.getPosition().getX() + 1, rrh.getPosition().getY()).isWolf()) {
                rrh.goDown();
                System.out.println("GO DOWN");
                printTestSolution(testSolution);

                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

                if (findPath(testSolution)) {

                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                            System.out.println("Has removed something");
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("INDEX OUT OF BOUND: goDown");
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }

            }
        }catch (ArrayIndexOutOfBoundsException e){}

        try {
            if (rrh.canGoLeft() && !field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() - 1)
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() - 1).isBear()
                    && !field.getCell(rrh.getPosition().getX(), rrh.getPosition().getY() - 1).isWolf()) {
                rrh.goLeft();
                System.out.println("GO LEFT");
                printTestSolution(testSolution);

                int sizeOfOldSolution = testSolution.size();

                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

                if (findPath(testSolution)) {
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution + 1; i < sizeOfNewSolution; i++) {
                        try {
                            testSolution.remove(i);
                            System.out.println("Has removed something");
                        }catch (IndexOutOfBoundsException e){
                            System.out.println("INDEX OUT OF BOUND: goLeft");
                        }
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){}

//        System.out.println("Corner!");
//        testSolution.clear();
//        field.setAllCellsClosed();
//        testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
//        findPath(testSolution);

        if(isAllAroundVisited()){
            System.out.println("Corner!");
            try {

                rrh.setPosition(new Position(testSolution.get(testSolution.size() - 2).getX(), testSolution.get(testSolution.size() - 2).getY()));
                testSolution.remove(testSolution.size() - 1);

                printTestSolution(testSolution);
                if (findPath(testSolution))
                    return true;

            }catch (IndexOutOfBoundsException e){
                System.out.println("No solution!");
                return false;
            }
        }
        //testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
        System.out.println("NO MOVE, THINK MUST BE CORNER");

        return false;
    }

    public boolean isAllAroundVisited() {
        return field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() - 1) && field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() + 1)
            &&  field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY()) && field.isOpen(rrh.getPosition().getX() + 1, rrh.getPosition().getY());
    }

    private void printTestSolution(List<Position> testSolution){
        for (int i = 0; i < testSolution.size(); i++) {
            System.out.println("TEST SOLUTION: " + testSolution.get(i).getX() + " " + testSolution.get(i).getY());
        }
    }
}
