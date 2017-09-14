package util;

/**
 * Created by ekaterina on 8/30/17.
 * Class for Constants, so they can be changed easily
 */
public class Constants {
    public final static int FIELD_SIZE = 9;//Size of Field
    public final static String EMPTY_SYMBOL = "▧";//Where RRH was (to print)
    public final static String CLOSED_SYMBOL = "✕";//Where RRH was not (to print)

    public final static String PATH_SYMBOL = "▧";//Where algorithm was
    public final static String GRASS_SYMBOL = "✕";//Where algorithm don't want to go

    public final static int CLOSED_CELL = 0;//Where RRH was not
    public final static int OPEN_CELL = 1;//Where RRH already was

    public final static int NUMBER_OF_TESTS = 100;//Number of tests
}
