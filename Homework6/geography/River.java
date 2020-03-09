package geography;

import java.util.*;
/**
 * Class defines a a river in a city
 */
public class River extends Geographical{

    ArrayList<City> cities;

    /**
     * sets river to empty
     */
    public River(){
        super();
    }

    /**
     * Sets name of river
     * @param name river's name
     */
    public River(String name){
        super(name);
    }

    /**
     * cities method to add a city
     * @param City river flows through
     */
    void addCity(City city){
        cities.add(city);
    }

    /**
     * @return cities
     */
    public ArrayList<City> getCities(){
        return cities;
    }
}
