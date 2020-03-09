package geography;

/**
 * Sets coordinate locations of objects
 */
public class Coordinates{

    private int horizontal;
    private int vertical;

    /**
     * Sets to negitive to indicate the coordinates are not set
     */
    public Coordinates(){
        horizontal = -1;
        vertical = -1;
    }

    /**
     * Sets coordinates
     * @param x horizontal
     * @param y vertical
     */
    public Coordinates(int x, int y){
        horizontal = x;
        vertical = y;
    }

    /**
     * @return horizontal
     */
    int getCoordinateX(){
        return horizontal;
    }

    /**
     * @param x
     */
    void setCoordinateX(int x){
        horizontal = x;
    }

    /**
     * @return vertical
     */
    int getCoordinateY(){
        return vertical;
    }
    
    /**
     * @param y
     */
    void setCoordinateY(int y){
        vertical = y;
    }
}
