import astar.AStarSearch;
import backtrack.BacktrackSearch;
import characters.RRH;
import util.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO print wrong maps only once
 */

public class Main {
    static RRH rrh;

    static String pathPictureAStar = "";
    static String pathPictureBacktrack = "";

    static double timeForAStar, timeForBacktrack;
    static int stepsAStar, stepsBackTrack;

    static PrintWriter wrongAnswerPrinter = null;

    public static void main(String[] args) {
        PrintWriter printWriter = null;
        PrintWriter prWriterAllInfo = null;

        try {
            printWriter = new PrintWriter("times.csv", "UTF-8");
            prWriterAllInfo = new PrintWriter("maps.txt", "UTF-8");
            wrongAnswerPrinter = new PrintWriter("wrongMaps.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        printWriter.print("Number of Map,Time for A*,Steps in A*,Time for Backtrack,Steps in Backtrack\n");//Columns headers for CSV file

        for (int k = 0; k < Constants.NUMBER_OF_TESTS; k++) {
            //Generation of new field
            Field field = new Field();
            field.newField();
            field.generateField();

            System.out.println("Current Field: ");
            field.showField();
            prWriterAllInfo.println(field.getStringField());

            //Start A*
            startAStarSearchTests(field);
            prWriterAllInfo.println(pathPictureAStar);

            //Start Backtrack
            startBacktrackSearchTests(field);
            prWriterAllInfo.println(pathPictureBacktrack);

            System.out.println("A-Star: " + timeForAStar + " Backtrack: " + timeForBacktrack);

            printWriter.print("#" + k + "," + timeForAStar + "," + stepsAStar + "," + timeForBacktrack + "," + stepsBackTrack + "\n");

        }
        printWriter.close();
        prWriterAllInfo.close();
        wrongAnswerPrinter.close();
    }

    /**
     * Running A-star algorithm for constant number of times
     */
    private static void startAStarSearchTests(Field field) {
        System.out.println("A-Star SEARCH");


        rrh = new RRH();
        long start = System.nanoTime();
        AStarSearch astar = new AStarSearch(rrh);
        long finish = System.nanoTime();
        List<Position> path = astar.startSearch();

        System.out.println("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i).getX() + " " + path.get(i).getY());
        }

        stepsAStar = path.size();
        if(path.size() == 1)
            wrongAnswerPrinter.println(field.getStringField());

        System.out.println("-------------------------------------");
        System.out.println("Path picture A-star: ");
        pathPictureAStar += "Path picture A-star: \n";
        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if (path.contains(new Position(i, j))) {
                    System.out.print(Constants.PATH_SYMBOL + "   ");
                    pathPictureAStar += Constants.PATH_SYMBOL + "   ";
                } else {
                    System.out.print(Constants.GRASS_SYMBOL + "   ");
                    pathPictureAStar += Constants.GRASS_SYMBOL + "   ";
                }
            }
            System.out.println();
            System.out.println();
            pathPictureAStar += "\n\n";
        }
        System.out.println("-------------------------------------");
        System.out.println("Time of A-Star algorithm: " + (finish - start));
        timeForAStar = finish - start;

    }

    /**
     * Running Backtrack algorithm for constant number of times
     */
    private static void startBacktrackSearchTests(Field field) {
        System.out.println("BACKTRACK SEARCH");

        rrh = new RRH();

        List<Position> pos = new LinkedList<>();
        pos.add(new Position(rrh.getPosition().getX(), rrh.getPosition().getY()));

        long start = System.nanoTime();
        BacktrackSearch backtrackSearch = new BacktrackSearch(rrh);
        boolean isPath = backtrackSearch.findPath(pos);
        long finish = System.nanoTime();

        if (!isPath) {
            System.out.println("No solution!!!!");
            wrongAnswerPrinter.println(field.getStringField());
        } else {

            System.out.println("Path: ");
            for (int i = 0; i < pos.size(); i++) {
                System.out.println(pos.get(i).getX() + " " + pos.get(i).getY());
            }

            System.out.println("Number of steps: " + pos.size());
            stepsBackTrack = pos.size();

            System.out.println("-------------------------------------");
            System.out.println("Path picture Backtrack: ");
            pathPictureBacktrack += "Path picture Backtrack: \n";
            for (int i = 0; i < Constants.FIELD_SIZE; i++) {
                for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                    if (pos.contains(new Position(i, j))) {
                        System.out.print(Constants.PATH_SYMBOL + "   ");
                        pathPictureBacktrack += Constants.PATH_SYMBOL + "   ";
                    } else {
                        System.out.print(Constants.GRASS_SYMBOL + "   ");
                        pathPictureBacktrack += Constants.GRASS_SYMBOL + "   ";
                    }
                }
                System.out.println();
                System.out.println();
                pathPictureBacktrack += "\n\n";
            }
            System.out.println("-------------------------------------");

            System.out.println("Time of Backtrack algorithm: " + (finish - start));
            timeForBacktrack = finish - start;
        }


    }


}
