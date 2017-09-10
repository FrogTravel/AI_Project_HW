package astar;

import util.Cell;
import util.Position;

/**
 * Created by ekaterina on 9/8/17.
 */
public class Node {
    private int Gcost;
    private int Hcost;
    private int Fcost;
    private Cell cell;
    private Position position;
    private Node parent;

    public Node(Cell cell, int x, int y) {
        position = new Position();
        this.cell = cell;
        position.setX(x);
        position.setY(y);
    }

    public Node() {
        position = new Position();
        Fcost = 100000;
        cell = new Cell();
        parent = null;
    }

    public Node(Cell cell, int x, int y, int gCost, int hCost) {
        position = new Position();
        this.cell = cell;
        position.setX(x);
        position.setY(y);
        setCosts(gCost, hCost);
    }

    public Node(Cell cell, Position position){
        this.position = position;
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getGcost() {
        return Gcost;
    }

    public void setGcost(int gcost) {
        Gcost = gcost;
    }

    public int getHcost() {
        return Hcost;
    }

    public void setHcost(int hcost) {
        Hcost = hcost;
    }

    public int getFcost() {
        return Fcost;
    }

    public void setFcost(int fcost) {
        Fcost = fcost;
    }

    public void setCosts(int gcost, int hcost) {
        this.Fcost = gcost + hcost;
        this.Gcost = gcost;
        this.Hcost = hcost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
}
