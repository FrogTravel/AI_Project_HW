package astar;

import util.Position;

/**
 * Node for A-Star algorithm
 */
public class StarNode {
    private boolean isOpen = false;//Was this node already visited
    private boolean traversable = true;//Is this node traversable

    private int FCost = -1;//F-Cost = G-Cost + H-Cost
    private int GCost = -1;//Distance between node and current node
    private int HCost = -1;//Distance between node and goal node

    private Position position;//Position of this node
    private StarNode parent = null;//Parent of this node

    public StarNode() {
        position = new Position(-1, -1);
    }

    /**
     * Getters and Setters
     */

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

    /**
     * Also counts F-cost
     */
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

