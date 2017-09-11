package util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ekaterina on 8/30/17.
 */
public class Cell {
    private Set<Integer> statuses = new HashSet<Integer>();

    public Cell() {
        this.setFree();
    }

    /**
     * Adds status to cell
     * @param status from public class STATUS
     */
    public void addStatus(int status) {
        if(statuses.contains(Status.FREE)){
            statuses.clear();
        }
        statuses.add(status);
    }

    /**
     * Removes character from cell and adds there FREE status, if cell is empty
     * @param status from public class STATUS
     */
    public void deleteCharacter(int status) {
        statuses.remove(status);
        if (statuses.isEmpty())
            statuses.add(Status.FREE);
    }


    /**
     * Getters and setters
     */

    public void setFree(){
        statuses.clear();
        statuses.add(Status.FREE);
    }

    public boolean isFree(){
        return statuses.contains(Status.FREE);
    }

    public boolean isCharacter(){
        return ((statuses.contains(Status.GRANNY)) || (statuses.contains(Status.BEAR))
                || statuses.contains(Status.WOLF) || statuses.contains(Status.RRH));
    }

    public Set<Integer> getStatuses(){
        return statuses;
    }

    public boolean isWoodcutter(){
        return statuses.contains(Status.WOOD_CUTTER);
    }



    public boolean isWolf() {
        return statuses.contains(Status.WOLF_RANGE) || statuses.contains(Status.WOLF);
    }

    public boolean isBear() {
        return statuses.contains(Status.BEAR_RANGE) || statuses.contains(Status.BEAR);
    }

    public boolean isFalseWoodcutter() {
        return statuses.contains(Status.FALSE_WOOD_CUTTER);
    }

    public boolean isGranny() {
        return statuses.contains(Status.GRANNY);
    }

    public boolean isRRHOnly() {
        return statuses.size() == 1 && statuses.contains(Status.RRH);
    }

}

