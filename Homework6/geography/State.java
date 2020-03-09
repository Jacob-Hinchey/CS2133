package geography;

import java.util.ArrayList;
/**
 * Class defines a state found in a country
 */
public class State extends Geographical{

    ArrayList<City> cities;
    City capitals;
    Country oldCountry;

    /**
     * Makes a empty state
     */
    State(){
        cities = new ArrayList<>();
    }

    /**
     * Makes a state with name
     * @param name state's name
     */
    State(String name){
        super(name);
        cities = new ArrayList<>();
    }

    /**
     * puts a city in the state
     * @param name city's name
     * @param location city's location
     * @param isCapitalCity boolean to deturmine capital
     */
    public void addCity(String name, Coordinates location, boolean isCapitalCity){
        City newCity = new City(this, name, location, isCapitalCity);
        if (isCapitalCity){
            capitals = newCity;
            this.setCoordinates(newCity.getCoordinates());
        }
        cities.add(newCity);
    }

    /**
     * puts city in state
     * @param City city to insert
     * @param isCapitalCity boolean to deturmine capital
     */
    public void addCity(City city, boolean isCapitalCity){
        city.Capital(isCapitalCity);
        city.setState(this);
        if (isCapitalCity){
            capitals = city;
            this.setCoordinates(city.getCoordinates());
        }
        cities.add(city);
    }

    /**
     * @param capitals sets capital
     */
    public void Capital(City capitals){
        this.capitals = capitals;
    }

    /**
     * @return cities in the state
     */
    public ArrayList<City> getCities(){
        return cities;
    }

    /**
     * @return country the state is in
     */
    public Country getCountry(){
        return oldCountry;
    }

    /**
     * @return state's area
     */
    public double area(){
        return super.area();
    }

    /**
     * @return capital
     */
    public City Capital(){
        return capitals;
    }

    /**
     * @param oldCountry sets larger country
     */
    public void setCountry(Country oldCountry){
        this.oldCountry = oldCountry;
    }

    /**
     * @param area sets area
     */
    public void area(double area){
        super.area(area);
    }
}
