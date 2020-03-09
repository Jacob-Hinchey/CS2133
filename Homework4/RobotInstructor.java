import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by kyle on 3/24/15.
 */
public class RobotInstructor {
    Socket robotSocket = null;
    PrintWriter commandWriter = null;

    //connection info
    final String HOST = "lear.cs.okstate.edu";
    final int PORT = 9095;

    //JSON constants to land and takeoff
    final String TAKEOFF_MSG = "{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}";
    final String LAND_MSG = "{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}";
    final String MOVE_FOREWARD_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}";
    final String MOVE_BACKWARD_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":-0.25,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}";
    final String MOVE_LEFT_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":-0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}";
    final String MOVE_RIGHT_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0.25,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}},\"type\":\"geometry_msgs/Twist\"}";
    final String ROTATE_LEFT_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0.25}},\"type\":\"geometry_msgs/Twist\"}";
    final String ROTATE_RIGHT_MESSAGE = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":-0.25}},\"type\":\"geometry_msgs/Twist\"}";

    //status codes
    final int SUCCESS = 1;
    final int FAILURE = 0;

    RobotInstructor(){
        try {
            robotSocket = new Socket(HOST, PORT);
            commandWriter = new PrintWriter(robotSocket.getOutputStream());
            System.out.println("socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSocket(){
        try {
            robotSocket = new Socket(HOST, PORT);
            commandWriter = new PrintWriter(robotSocket.getOutputStream());
            System.out.println("socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int commandTakeoff(){
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(TAKEOFF_MSG);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandLand(){
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(LAND_MSG);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandMoveForeward(){
        System.out.println("foreward");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(MOVE_FOREWARD_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandMoveBackward(){
        System.out.println("backward");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(MOVE_BACKWARD_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandMoveLeft(){
        System.out.println("left");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(MOVE_LEFT_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandMoveRight(){
        System.out.println("right");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(MOVE_RIGHT_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandRotateLeft(){
        System.out.println("rotate left");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(ROTATE_LEFT_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    public int commandRotateRight(){
        System.out.println("rotate right");
        if(robotSocket != null && commandWriter != null){
            commandWriter.print(ROTATE_RIGHT_MESSAGE);
            commandWriter.flush();

            return SUCCESS;
        } else {
            return FAILURE;
        }
    }
}
