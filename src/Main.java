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
    static double averageBacktrack;
    static double averageAStar;


    public static void main(String[] args) {
        //startBacktrackSearchTests();
        startAStarSearchTests();
        System.out.println("Average of steps A-STAR: " + averageAStar);
        //System.out.println("Average of steps BACKTRACK: " + averageBacktrack);
    }

    /**
     * Running A-star algorithm for constant number of times
     */
    private static void startAStarSearchTests(){
        System.out.println("A-Star SEARCH");
        List<Integer> numberOfSteps = new LinkedList<>();
        for (int k = 0; k < Constants.NUMBER_OF_TESTS; k++) {
            field = new Field();
            field.newField();
            field.generateField();

            System.out.println("Current Field: ");
            field.showField();

            rrh = new RRH();
            AStarSearch astar = new AStarSearch(rrh);
            List<Position> path = astar.startSearch();

            System.out.println("Path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.println(path.get(i).getX() + " " + path.get(i).getY());
            }

            numberOfSteps.add(path.size());

            System.out.println("-------------------------------------");
            System.out.println("Path picture: ");
            for (int i = 0; i < Constants.FIELD_SIZE; i++) {
                for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                    if (path.contains(new Position(i, j)))
                        System.out.print(Constants.PATH_SYMBOL + "   ");
                    else
                        System.out.print(Constants.GRASS_SYMBOL + "   ");
                }
                System.out.println();
                System.out.println();
            }
            System.out.println("-------------------------------------");
        }

        for (int i = 0; i < numberOfSteps.size(); i++) {
            averageAStar += numberOfSteps.get(i);
        }

        averageAStar /= numberOfSteps.size();
    }

    /**
     * Running Backtrack algorithm for constant number of times
     */
    private static void startBacktrackSearchTests(){
        System.out.println("BACKTRACK SEARCH");
        List<Integer> numberOfSteps = new LinkedList<>();
        for (int k = 0; k < Constants.NUMBER_OF_TESTS; k++) {
            System.out.println("--------- TEST NUMBER: " + (k + 1) + " -----------");

            field = new Field();
            field.newField();
            field.generateField();
            field.showField();

            rrh = new RRH();

            List<Position> pos = new LinkedList<>();
            pos.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

            BacktrackSearch backtrackSearch = new BacktrackSearch(rrh);

            if (!backtrackSearch.findPath(pos))
                System.out.println("No solution!!!!");
            else {

                System.out.println("Path: ");
                for (int i = 0; i < pos.size(); i++) {
                    System.out.println(pos.get(i).getX() + " " + pos.get(i).getY());
                }

                numberOfSteps.add(pos.size());
                System.out.println("Number of steps: " + pos.size());

                System.out.println("-------------------------------------");
                System.out.println("Path picture: ");
                for (int i = 0; i < Constants.FIELD_SIZE; i++) {
                    for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                        if (pos.contains(new Position(i, j)))
                            System.out.print(Constants.PATH_SYMBOL + "   ");
                        else
                            System.out.print(Constants.GRASS_SYMBOL + "   ");
                    }
                    System.out.println();
                    System.out.println();
                }
                System.out.println("-------------------------------------");
            }
        }

        for (int i = 0; i < numberOfSteps.size(); i++) {
            averageBacktrack += numberOfSteps.get(i);
        }
        averageBacktrack /= numberOfSteps.size();


    }


}
