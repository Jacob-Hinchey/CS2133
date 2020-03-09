import java.io.*;
import java.net.*;

public class Webserver {
    public static void main (String args[]){
        ServerSocket connect = null;
        try {
            connect = new ServerSocket(8000);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        while (true){
            try{
                Thread connection = new Thread(new ConnectionToServer(connect.accept()));
                connection.start();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
