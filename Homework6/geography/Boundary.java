package geography;

import java.util.*;

/**
 * Class defines separations between different objects
 */
public class Boundary{

    private Coordinates start;
    private Coordinates finish;
    private int count = 0;
    private int bound;

    ArrayList<Geographical> locations;
    /**
     * @param start shows the beginning of the boundary
     * @param finish shows the beginning of the boundary
     */
    Boundary(Coordinates start, Coordinates finish){
        this.start = start;
        this.finish = finish;
        count++;
        this.bound = count;
    }

    /**
     * @return The legnth of the boundary
     */
    public double boundaryLength(){
        double length;
        int X_a = start.getCoordinateX();
        int X_b = finish.getCoordinateX();
        int Y_a = start.getCoordinateY();
        int Y_b = finish.getCoordinateY();
        length = Math.sqrt(Math.pow((X_b - X_a), 2) + Math.pow((Y_b - Y_a), 2));
        return length;
    }

    /**
     * @return locations to use to set boundaries
     */
    public ArrayList<Geographical> borderOf(){
        return locations;
    }

    /**
     * @param Place to add the boundary
     */
    void addPlace(Geographical place){
     locations.add(place);
    }

    /**
     * @return bound to label boundaries
     */
    int getBound(){
        return bound;
    }
}
