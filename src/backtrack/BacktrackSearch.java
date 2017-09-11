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

    public boolean isAllAroundVisited() {
        return field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() - 1) && field.isOpen(rrh.getPosition().getX(), rrh.getPosition().getY() + 1)
            &&  field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY()) && field.isOpen(rrh.getPosition().getX() + 1, rrh.getPosition().getY());
    }

}
