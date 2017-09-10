package astar;

import util.Position;

/**
 * Created by ekaterina on 9/9/17.
 */
public class StarNode {
    private boolean isOpen = false;
    private boolean traversable = true;

    private int FCost = -1;
    private int GCost = -1;
    private int HCost = -1;

    private Position position;
    private StarNode parent = null;

    public StarNode() {
        position = new Position(-1, -1);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getFCost() {
        return FCost;
    }

    public void setFCost(int FCost) {
        this.FCost = FCost;
    }

    public int getGCost() {
        return GCost;
    }

    public void setGCost(int GCost) {
        this.GCost = GCost;
    }

    public int getHCost() {
        return HCost;
    }

    public void setHCost(int HCost) {
        this.HCost = HCost;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
    }

    public void setCosts(int Gcost, int Hcost) {
        this.GCost = Gcost;
        this.HCost = Hcost;
        this.FCost = Gcost + Hcost;
    }

    public StarNode getParent() {
        return parent;
    }

    public void setParent(StarNode parent) {
        this.parent = parent;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }
}

