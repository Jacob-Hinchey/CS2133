package geography;

import java.util.*;
/**
 * Class defines a certain location in a state
 */
public class City extends Geographical{

    boolean isCapitalCity;
    State state;
    ArrayList<River> rivers;

    /**
     * No information for the city
     */
    City(){
        super();
        isCapitalCity = false;
        rivers = new ArrayList<>();
    }

    /**
     * @param location city with coordinates
     */
    City(Coordinates location){
        super(location);
        isCapitalCity = false;
        rivers = new ArrayList<>();
    }


    /**
     * @param name city with a name
     */
    City(String name){
        super(name);
        isCapitalCity = false;
        rivers = new ArrayList<>();
    }


    /**
     * @param name name for the city
     * @param location coordinates for the city
     */
    City(String name, Coordinates location){
        super(name, location);
        isCapitalCity = false;
        rivers = new ArrayList<>();
    }

    /**
     * city with a name, location, and capital status
     */
    City(String name, Coordinates location, boolean isCapitalCity) {
        super(name, location);
        this.isCapitalCity = isCapitalCity;
        rivers = new ArrayList<>();
    }

    /**
     * city with a state, name, location, and capital status
     */
    City(State state, String name, Coordinates location, boolean isCapitalCity){
        super(name, location);
        this.state = state;
        this.isCapitalCity = isCapitalCity;
        rivers = new ArrayList<>();
    }

    /**
     * city with a state, name, location, capital status, and area
     */
    City(State state, String name, Coordinates location, boolean isCapitalCity, double area){
        super(name, location, area);
        this.state = state;
        this.isCapitalCity = isCapitalCity;
        rivers = new ArrayList<>();
    }


    /**
     * @return distance between two cities
     */
    public static double distance(City first, City second) {
        double distance;
        geography.Coordinates coordinatesA = first.getCoordinates();
        geography.Coordinates coordinatesB = second.getCoordinates();
        int x_a = coordinatesA.getCoordinateX();
        int x_b = coordinatesB.getCoordinateX();
        int y_a = coordinatesA.getCoordinateY();
        int y_b = coordinatesB.getCoordinateY();
        double t1 = Math.pow((x_b - x_a), 2);
        double t2 = Math.pow((y_b - y_a), 2);
        distance = Math.sqrt(t1 + t2);
        return distance;
    }

    /**
     * @return true if city if capital
     */
    public boolean Capital(){
        return isCapitalCity;
    }

    /**
     * @param isCapitalCity true or false on if the city is a capital
     */
    public void Capital(boolean isCapitalCity){
        this.isCapitalCity = isCapitalCity;
    }

    /**
     * @return state the city is in
     */
    public State getState(){
        return this.state;
    }

    /**
     * @return returns rivers in the city
     */
    public ArrayList<River> getRivers() {
        return rivers;
    }

    /**
     * @return area
     */
    public double area(){
        return super.area();
    }

    /**
     * @param parentState sets state the city is in
     */
    public void setState(State oldState){
        this.state = oldState;
    }

    /**
     * @param River puts river in city
     */
    public void addRiver(River river) {
        river.addCity(this);
        rivers.add(river);
    }

    /**
     * @param area sets the area
     */
    public void area(double area){
        super.area(area);
    }
}
