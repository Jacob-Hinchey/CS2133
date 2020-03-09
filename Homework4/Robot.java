import java.util.*;
import java.io.*;
import java.net.*;

public class Robot {

    public static void main(String[] args){
        MovementsForRobot movementsForRobot = new MovementsForRobot();
        System.out.println("Each movement is attached to a number to preform the action: \n 1 - Takeoff \n 2 - Forward \n 3 - Backward \n 4 - Move to the Left \n 5 - Move to the Right \n 6 - Rotate Left \n 7 - Rotate Right \n 8 - Land");
        Scanner input = new Scanner(System.in);
        while (input.hasNextInt()){
            if(input.nextInt() == 1){
              movementsForRobot.takeoff();
            }
            else if(input.nextInt() == 2){
              movementsForRobot.moveForward();
            }
            else if(input.nextInt() == 3){
              movementsForRobot.moveBackward();
            }
            else if(input.nextInt() == 4){
              movementsForRobot.moveLeft();
            }
            else if(input.nextInt() == 5){
              movementsForRobot.moveRight();
            }
            else if(input.nextInt() == 6){
              movementsForRobot.turnLeft();
            }
            else if(input.nextInt() == 7){
              movementsForRobot.turnRight();
            }
            else if(input.nextInt() == 8){
              movementsForRobot.land();
            }
            else{
              System.out.println("Not a valid command.");
            }
        }
    }
}

class MovementsForRobot {

    Socket okstate = null;
    PrintWriter movement = null;
    public void connectToOkstate(){
        try {
            okstate = new Socket("lear.cs.okstate.edu", 9095);
            movement = new PrintWriter(okstate.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    MovementsForRobot(){
        try {
            okstate = new Socket("lear.cs.okstate.edu", 9095);
            movement = new PrintWriter(okstate.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public int complete = 1;
    public int incomplete = 0;
    //recieved lasso center help for stratagy to implement movements
    public int takeoff(){
        if(okstate != null && movement != null){
            movement.print("{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int moveForward(){
        System.out.println("foreward");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int moveBackward(){
        System.out.println("backward");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":-0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int moveLeft(){
        System.out.println("left");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":-0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int moveRight(){
        System.out.println("right");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int turnLeft(){
        System.out.println("rotate left");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0.25}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int turnRight(){
        System.out.println("rotate right");
        if(okstate != null && movement != null){
            movement.print("{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":-0.25}},\"type\":\"geometry_msgs/Twist\"}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }

    public int land(){
        if(okstate != null && movement != null){
            movement.print("{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}");
            movement.flush();
            return complete;
        }
        else {
            return incomplete;
        }
    }
}
