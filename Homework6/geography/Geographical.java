package geography;

import java.util.*;

/**
 * base class for other objects
 */
public class Geographical{

    private Coordinates coordinates;
    private String name;
    private ArrayList<Boundary> bounderies;
    private double area;

    /**
     * Sets both coordinates and name to empty
     */
    Geographical(){
        coordinates = new Coordinates();
        name = null;
    }

    /**
     * Makes object with location
     * @param location sets location of object with coordinates
     */
    Geographical(Coordinates location){
        this.coordinates = location;
        this.name = null;
        this.area = -1;
    }

    /**
     * Makes object with name
     * @param name sets name of object
     */
    Geographical(String name){
        this.coordinates = null;
        this.name = name;
        this.area = -1;
    }

    /**
     * Makes object with name and area
     * @param name sets name in object
     * @param area sets area in object
     */
    Geographical(String name, double area){
        this.coordinates = null;
        this.name = name;
        this.area = area;
    }

    /**
     * Makes object with location and name
     * @param name sets name of object
     * @param location sets location of object
     */
    Geographical(String name, Coordinates location){
        this.coordinates = location;
        this.name = name;
        this.area = -1;
    }

    /**
     * Makes object with name, location, and area
     * @param name sets name in object
     * @param location sets location in object
     * @param area sets area in object
     */
    Geographical(String name, Coordinates location, double area){
        this.coordinates = location;
        this.name = name;
        this.area = area;
    }

    /**
     * @return coordinates
     */
    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    /**
     * @param Coordinates sets coordinates
     */
    public void setCoordinates(Coordinates coordinates){
        coordinates = coordinates;
    }

    /**
     * @param x sets new X
     * @return returns ealier X
     */
    int setCoordinateX(int x){
        int prevX = coordinates.getCoordinateX();
        coordinates.setCoordinateX(x);
        return prevX;
    }

    /**
     * @param y sets new Y
     * @return returns earlier Y
     */
    int setCoordinatesY(int y){
        int prevY = coordinates.getCoordinateX();
        coordinates.setCoordinateY(y);
        return prevY;
    }


    /**
     * @return name
     */
    String getName(){
        return name;
    }

    /**
     * Arraylist is made by checking places for their boundaries and checks their titles
     * @return neighbors that are near the place
     */
    public ArrayList<Geographical> neighbors(ArrayList<Geographical> places){
        ArrayList<Geographical> Neighbors = new ArrayList<>();
        return Neighbors;
    }

    /**
     * @return area
     */
    double area(){
        return area;
    }

    /**
     * @param area area set
     */
    void area(double area){
        this.area = area;
    }

    /**
     * @param name name of object
     * @return returns earlier name
     */
    String setName(String name){
        String prevName = this.name;
        this.name = name;
        return prevName;
    }

    /**
     * @param Boundary sets boundary
     */
    public void addBoundary(Boundary boundery){
        boundery.addPlace(this);
        bounderies.add(boundery);
    }
}
