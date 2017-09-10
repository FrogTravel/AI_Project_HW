package astar;

import characters.Granny;
import characters.RRH;
import util.Constants;
import util.Field;
import util.HiddenField;
import util.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ekaterina on 9/8/17.
 * site: https://www.youtube.com/watch?v=-L-WgKMFuhE
 */
public class AStarAlgorithm {
    List<Node> opened;//the set of nodes to be evaluated
    List<Node> closed;//the set of nodes already evaluated
    Stack<Node> path;
    Node goalNode;
    Node startNode;

    private RRH rrh;

    public AStarAlgorithm(RRH rrh) {
        this.rrh = rrh;
        HiddenField field = rrh.getField();

        opened = new LinkedList<>();
        closed = new LinkedList<>();
        path = new Stack<>();
        goalNode = new Node(rrh.getField().getGranniesCell(), Granny.getPosition());
        startNode = new Node(rrh.getField().getCell(0,0), rrh.getPosition());

        for (int i = 0; i < Constants.FIELD_SIZE; i++){
            for (int j = 0; j < Constants.FIELD_SIZE; j++){
                opened.add(new Node(field.getCell(i, j), i, j, 10000, 10000));
            }
        }

        findPath();

        Node current = goalNode;
        while (current.getPosition().equals(startNode.getPosition())){
            path.add(current);
            current = current.getParent();
        }

        System.out.println("Path");
        for (Node node : path) {
            System.out.println(node.getPosition());
        }
    }


    private void findPath(){
//        opened.add(closed.get(0));//on position of rrh (0,0)
//        closed.remove(0);

//        for (int i = 0; i < closed.size(); i++) {
//            if(isRRHPosition(i)) {
//                opened.add(closed.get(i).setCosts(-1,-1));
//                closed.remove(i);
//            }else if(isRRHPosition(i)){
//                opened.add(closed.get(i).setCosts(10, countHcost(closed.get(i).getPosition(), Granny.getPosition())));//for neubourg cells it always will be 10, because no diagonal moves are allowed
//                closed.remove(i);
//            }
//        }
//
//        Node current;
//        while(true){
//            current = getNodeWithLoswestFCost(opened);
//            opened.remove(current);
//            closed.add(current);
//        }

        //opened.add(closed.get(0));//position(0,0) of rrh
        Node current = new Node();
        opened.get(0).setCosts(0,0);



        while (true) {
            current = getNodeWithLoswestFCost(opened);
            System.out.println("Current F-cost: " + current.getFcost());
            opened.remove(current);
            closed.add(current);

            if(current.getPosition().equals(goalNode.getPosition())){//path has been found (somehow...)
                return;
            }

            for (int i = 0; i < opened.size(); i++){
                if ((opened.get(i) != null) && (isRRHPositionNeirbourg(opened, i))){//is in open and neihbour
                    Node neighbour = opened.get(i);
                    if((countGcost(current.getPosition(), neighbour.getPosition())) < (neighbour.getGcost())) {
                        neighbour.setCosts(countGcost(current.getPosition(), neighbour.getPosition()), countHcost(current.getPosition(), goalNode.getPosition()));//TODO maybe problem, may sum up Gcost
                        neighbour.setParent(current);
                    }

                }
            }

            for (int i = 0; i < closed.size(); i++){
                if ((closed.get(i) != null) && (isRRHPositionNeirbourg(closed, i))){//is in open and neighbour
                    Node neighbour = opened.get(i);
                    if((countGcost(current.getPosition(), neighbour.getPosition())) < (neighbour.getGcost())) {
                        neighbour.setCosts(countGcost(current.getPosition(), neighbour.getPosition()), countHcost(current.getPosition(), goalNode.getPosition()));//TODO maybe problem, may sum up Gcost
                        neighbour.setParent(current);
                    }

                }
            }

            printField();
        }

    }

    private Node getNodeWithLoswestFCost(List<Node> nodeList){
        Node min = new Node();
        for (int i = 0; i < nodeList.size(); i++) {
            if(min.getFcost() > nodeList.get(i).getFcost())
                min = nodeList.get(i);
        }
        return min;
    }

    private int countGcost(Position parentPosition, Position neubourgPosition) {
        return (Math.abs(parentPosition.getX() - neubourgPosition.getX()) + Math.abs(parentPosition.getY() - neubourgPosition.getY())) * 10;
    }

    private int countHcost(Position cellPosition, Position goalPosition) {
        return (Math.abs(goalPosition.getX() - cellPosition.getX()) + Math.abs(goalPosition.getY() - cellPosition.getY())) * 10;
    }

    private boolean isRRHPosition(int i){
        return (opened.get(i).getPosition().getX() == rrh.getPosition().getX()) && (opened.get(i).getPosition().getY() == rrh.getPosition().getY());
    }

    private boolean isRRHPositionNeirbourg(List<Node> list, int i){
        return (list.get(i).getPosition().getX() == rrh.getPosition().getX() + 1) && (list.get(i).getPosition().getY() == rrh.getPosition().getY())
                || (list.get(i).getPosition().getX() == rrh.getPosition().getX()) && (list.get(i).getPosition().getY() == rrh.getPosition().getY() + 1)
                || ((list.get(i).getPosition().getX() == rrh.getPosition().getX() - 1) && (list.get(i).getPosition().getY() == rrh.getPosition().getY())
                || ((list.get(i).getPosition().getX() == rrh.getPosition().getX()) && (list.get(i).getPosition().getY() == rrh.getPosition().getY() - 1)));
    }

    private void printField(){
        Node[][] field = new Node[Constants.FIELD_SIZE][Constants.FIELD_SIZE];
        int counter = 0;
        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if((counter < opened.size()) && (opened.get(counter) != null))
                    System.out.print(opened.get(counter).getFcost() + " ");

                else if((counter < opened.size()) && (closed.get(counter) != null)){
                    System.out.print(closed.get(counter).getFcost() + " ");
                }else{
                    System.out.println("WHAT THE FUCK!!");
                }

                counter++;
                //System.out.print(field[i][j].getFcost() + " ");
            }
            System.out.println();
        }

        counter = 0;
        for (int i = 0; i < Constants.FIELD_SIZE; i++) {
            for (int j = 0; j < Constants.FIELD_SIZE; j++) {
                if((counter < opened.size()) && (opened.get(counter) != null))
                    System.out.print("O" + " ");

                else if((counter < opened.size()) && (closed.get(counter) != null)){
                    System.out.print("C" + " ");
                }else{
                    System.out.println("WHAT THE FUCK!!");
                }

                counter++;
                //System.out.print(field[i][j].getFcost() + " ");
            }
            System.out.println();
        }
    }
}

