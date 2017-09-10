package util;

/**
 * Created by ekaterina on 8/30/17.
 */
public class Position {
    private int x;
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void change(int x, int y) {
        if(x >= Constants.FIELD_SIZE)
            x = Constants.FIELD_SIZE - 1;
        if(y >= Constants.FIELD_SIZE)
            y = Constants.FIELD_SIZE - 1;

        if(x < 0)
            x = 0;
        if(y < 0)
            y = 0;

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Position position = (Position) o;

        return  (this.getX() == position.getX()) && (this.getY() == position.getY());
    }
}
