package geography;

import java.util.*;

/**
 * Class defines a certain large location containing smaller locations
 */
public class Country extends Geographical{
    ArrayList<State> states;

    /**
     * @param name Country's name
     * @param area Country's area
     */
    Country(String name, double area){
        super(name, area);
    }

    /**
     * @param name Country's name
     */
    Country(String name){
        super(name);
    }

    /**
     * @param State Puts state in its country
     */
    public void addState(State state){
        state.setCountry(this);
        states.add(state);
    }

    /**
     * @return states
     */
    public ArrayList<State> getStates(){
        return states;
    }

    /**
     * @return area
     */
    public double area(){
        return super.area();
    }

    /**
     * @param area Sets area
     */
    public void area(double area){
        super.area(area);
    }

}
