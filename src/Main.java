import astar.AStarAlgorithm;
import astar.AStarSearch;
import backtrack.BacktrackSearch;
import characters.Granny;
import characters.RRH;
import characters.Wolf;
import characters.WoodCutter;
import util.*;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static RRH rrh;
    static Field field;
    static List<Position> partialPath;

    public static void main(String[] args)  {
	// write your code here

        field = new Field();

        field.newField();
        field.generateField();
        field.showField();

        rrh = new RRH();
        partialPath = new LinkedList<>();
        //partialPath.add(new util.Position(0,0));

        System.out.println(Granny.getPosition().getX() + " " + Granny.getPosition().getY());
        //System.out.println(findPath(Granny.getPosition()));

        List<Position> pos = new LinkedList<>();
        pos.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
        //findPath(pos);

        BacktrackSearch backtrackSearch = new BacktrackSearch(rrh);

        backtrackSearch.findPath(pos);

        for (int i = 0; i < pos.size(); i++) {
            System.out.println(pos.get(i).getX() + " " + pos.get(i).getY());
        }

        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if (pos.contains(new Position(i, j)))
                    System.out.print(Constants.EMPTY_SYMBOL + " ");
                else
                    System.out.print(Constants.CLOSED_SYMBOL + " ");
            }
            System.out.println();
        }

        //rrh.getField().showField();

        //AStarSearch astar = new AStarSearch(rrh);

//        characters.RRH rrh = null;
//        try {
//            rrh = new characters.RRH();
//        } catch (util.exceptions.GameOverException e) {
//            gameEnd(rrh);
//        } catch (util.exceptions.GameWinException e) {
//            gameWin(rrh);
//        }
//
//        rrh.getField().showField();
//
//        Scanner scanner = new Scanner(System.in);
//        String userInput;
//
//        while(true){
//            System.out.println("Where to go: U - UP, D - Down, L - Left, R - right, E - end the game\nS - show field");
//            userInput = scanner.nextLine();
//            try {
//                switch (userInput) {
//                    case "U":
//                        rrh.goUp();
//                        break;
//                    case "D":
//                        rrh.goDown();
//                        break;
//                    case "L":
//                        rrh.goLeft();
//                        break;
//                    case "R":
//                        rrh.goRight();
//                        break;
//                    case "E":
//                        gameEnd(rrh);
//                        break;
//                }
//            }catch (util.exceptions.GameOverException e){
//                gameEnd(rrh);
//                break;
//            } catch (util.exceptions.GameWinException e) {
//                gameWin(rrh);
//                break;
//            }
//            rrh.getField().showField();
//            System.out.println("Berries: " + rrh.getBerries());
//        }
    }

    private static void gameEnd(RRH rrh){
        System.out.println("GAME OVER");
        System.out.println("Berries: " + rrh.getBerries());
        rrh.getField().showField();
    }

    private static void gameWin(RRH rrh){
        System.out.println("YOU WIN THE GAME!!!");
    }

//    private static boolean findPath( Position goal) {
//        Position currentPosition = rrh.getPosition();
//        Cell currentCell = field.getCell(currentPosition.getX(), currentPosition.getY());
//        if (currentCell.isWolf()) {
//            return false;
//        } else if (currentCell.isRRHOnly()) {
//            partialPath.add(currentPosition);
//        } else if (currentCell.isBear()) {
//            //TODO
//            goal = WoodCutter.getRealPosition();
//            //Temporary
//            //return false;
//        } else if (currentCell.isWoodcutter()) {
//            partialPath.add(currentPosition);
//            //TODO
//        } else if (currentCell.isFalseWoodcutter()) {
//            partialPath.add(currentPosition);
//            //TODO
//        } else if (currentCell.isGranny()) {
//            if (rrh.getBerries() == 6)
//                return true;
//            else
//                return false;
//        }
//
//        rrh.getField().showField();
//
////        if(rrh.canGoUp()) {
////            rrh.goUp();
////            if (findPath(goal)) {
////                //partialPath.addAll(testSolution);
////                return true;
////            }
////        }
////
////        if(rrh.canGoRight()) {
////            rrh.goRight();
////            if (findPath(goal)) {
////                //partialPath.addAll(testSolution);
////                return true;
////            }
////        }
////
////        if(rrh.canGoDown()) {
////            rrh.goDown();
////            if (findPath(goal)) {
////                //partialPath.addAll(testSolution);
////                return true;
////            }
////        }
////
////        if(rrh.canGoLeft()) {
////            rrh.goLeft();
////            if (findPath(goal)) {
////                //partialPath.addAll(testSolution);
////                return true;
////            }
////        }
//        System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " Goal: " + goal.getX() + " " + goal.getY());
//        if((currentPosition.getY() < goal.getY()) && (field.getCell(currentPosition.getX(),currentPosition.getY() + 1) != null)
//                && (!field.getCell(currentPosition.getX(), currentPosition.getY() + 1).isWolf())
//                && (!field.getCell(currentPosition.getX(), currentPosition.getY() + 1).isBear())) {
//            rrh.goRight();
//        }else if(currentPosition.getY() > goal.getY() && (field.getCell(currentPosition.getX(),currentPosition.getY() - 1) != null)
//                && (!field.getCell(currentPosition.getX(), currentPosition.getY() - 1).isWolf())
//                && (!field.getCell(currentPosition.getX(), currentPosition.getY() - 1).isBear())){
//            rrh.goLeft();
//        }else if(currentPosition.getX() < goal.getX() && (field.getCell(currentPosition.getX() + 1,currentPosition.getY()) != null)
//                && (!field.getCell(currentPosition.getX() + 1, currentPosition.getY()).isWolf())
//                && (!field.getCell(currentPosition.getX() + 1, currentPosition.getY()).isBear())){
//            rrh.goDown();
//        }else if(currentPosition.getX() > goal.getX() && (field.getCell(currentPosition.getX() - 1,currentPosition.getY()) != null)
//                && (!field.getCell(currentPosition.getX() - 1, currentPosition.getY()).isWolf())
//                && (!field.getCell(currentPosition.getX() - 1, currentPosition.getY()).isBear())){
//            rrh.goUp();
//        }else{
//            System.out.println("WTF!!!!");
//            return false;
//        }
//
//        if(findPath(goal) && goal.getX() == WoodCutter.getRealPosition().getX() && goal.getY() == WoodCutter.getRealPosition().getY())
//            goal = Granny.getPosition();
//
//        if(findPath(goal)) {
//            System.out.println("Found goal");
//            return true;
//        }
//        return false;
//    }

    List<Position> partSolution = new LinkedList<>();
    Position goal = Granny.getPosition();
    private static int steps = 0;

    private static boolean findPath(List<Position> testSolution){
        //List<Position> testSolution = new LinkedList<>();
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

        HiddenField field = rrh.getField();

        try {
            if (rrh.canGoUp() && !field.isOpen(rrh.getPosition().getX() - 1, rrh.getPosition().getY())
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isBear()
                    && !field.getCell(rrh.getPosition().getX() - 1, rrh.getPosition().getY()).isWolf()) {
                rrh.goUp();
                int sizeOfOldSolution = testSolution.size();
                testSolution.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));
                if (findPath(testSolution)) {
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();
                    for (int i = sizeOfOldSolution; i < sizeOfNewSolution; i++) {
                        testSolution.remove(i);
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
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution; i < sizeOfNewSolution; i++) {
                        testSolution.remove(i);
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

                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution; i < sizeOfNewSolution; i++) {
                        testSolution.remove(i);
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
                    //partialPath.addAll(testSolution);
                    return true;
                } else {
                    int sizeOfNewSolution = testSolution.size();

                    for (int i = sizeOfOldSolution; i < sizeOfNewSolution; i++) {
                        testSolution.remove(i);
                    }
                    rrh.setPosition(testSolution.get(testSolution.size() - 1));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){}

        return false;
    }



}
