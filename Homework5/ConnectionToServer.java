import java.io.*;
import java.net.*;
import java.util.regex.*;

public class ConnectionToServer implements Runnable{

    private Socket connect;
    private BufferedWriter writer;
    private File in;
    ConnectionToServer(Socket connect){
        this.connect = connect;
    }
    public void run() {
        String input = "";
        String file = fileNamer(input);
        String title = "";
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while ((input = reader.readLine()) != null){
                if(input.startsWith("GET")){
                    break;
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        if(file != null){
            title = "HTTP/1.1 200 OK\r\n";
        }
        else{
            title = "HTTP/1.1 404 Not Found\r\n\r\n";
        }
        try{
            in = new File(file);
            if(!in.exists()){
                title = "HTTP/1.1 404 Not Found\r\n\r\n";
                writer = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                if(new File("404.html").exists()){
                    serverNotFound();
                }
                else{
                    writer.write(title);
                    writer.flush();
                    writer.close();
                }
                return;
            }
            else{
                writer = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                writer.write("HTTP/1.1 500 Internal Server Error\r\n\r\n");
            }
        }
        catch (IOException ex){
            System.out.println("IOException");
        }
        try{
            if(title.equals("HTTP/1.1 200 OK\r\n")){
                if(file.endsWith(".jpg")){
                    writer.write("Content-type: image/jpg\r\n\r\n");
                    in = new File(file);
                    FileInputStream readImage = new FileInputStream(in);
                    byte[] byteArr = new byte[(int)in.length()];
                    readImage.read(byteArr);
                    String out = new String(byteArr, "UTF-8");
                    writer.write(out);
                    writer.flush();
                    writer.close();
                }
                else{
                    in = new File(file);
                    FileInputStream fileReader = new FileInputStream(in);
                    byte[] byteArr = new byte[(int)in.length()];
                    fileReader.read(byteArr);
                    String out = new String(byteArr, "UTF-8");
                    writer.write(out);
                    writer.flush();
                    writer.close();
                }
            }
        }
        catch (IOException ex){
            System.out.println("IOException");
        }
    }
    public void serverNotFound(){
        try{
            BufferedReader notAvalible = new BufferedReader(new FileReader(new File("404.html")));
            String out = "";
            String str = "";
            while ((str = notAvalible.readLine()) != null){
                out += str;
            }
            if(writer != null){
                writer.write("HTTP/1.1 200 OK\r\n");
                writer.write("Content-type: text/html\r\n\r\n");
                writer.write(out);
                writer.flush();
                writer.close();
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("404 page not found");
        }
        catch (IOException ex){
            System.out.println("IOException in serverNotFound");
        }
    }

    public String fileNamer(String input){
        String file = "";
        if(input == null){
            return "index.html";
        }
        Matcher matcher = Pattern.compile("GET\\s\\/(.*?)\\sHTTP\\/1\\.1", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(input);
        try{
            while (matcher.find()){
                file = matcher.group(1);
            }
        }
        catch (Exception ex){
            System.out.println("No file found in request");
            return null;
        }
        if(file.equals("/") || file.equals("") || file == null){
            return "index.html";
        }
        else{
            return file;
        }
    }
}
