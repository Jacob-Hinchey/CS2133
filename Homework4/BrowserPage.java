import java.util.regex.*;
import java.net.*;
import java.io.*;

public class BrowserPage{
  public String pageText = "";
  public String pageHeader = "";
  public String pageBody = "";
  private void reduceHeader(){
     String header = "";
     Matcher findExpression;
     findExpression = Pattern.compile("<title[^>]*>(.*?)</title>",
                      Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(pageText);
     try
     {
         while(findExpression.find()){
           header = findExpression.group(1);
         }
     }
     catch(IllegalStateException ex)
     {
         ex.printStackTrace();
     }
     this.pageHeader = header;
  }
  private void reducePage(){
     String body = "";
     String page = "";
     Matcher findExpression = Pattern.compile("<body[^>]*>(.*?)</body>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(pageText);
     try
     {
         while(findExpression.find()){
           body = findExpression.group(1);
         }
     }
     catch(IllegalStateException ex)
     {
         ex.printStackTrace();
     }
     //recieved lasso help for HTML formatting
     page = body.replaceAll("<script[^>]*>(.*?)</script>", "")
                .replaceAll("<select[^>]*>(.*?)</select>", "")
                .replaceAll("<input[^>]*>(.*?)</input>", "")
                .replaceAll("<[^>]*>", "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("-[^>]*>", "")
                .replaceAll("  +", "\n")
                .trim();
     this.pageBody = page;
   }

   public BrowserPage(String pageText){
      this.pageText = pageText;
      reducePage(this.pageText);
   }

   public void reducePage(String pageText){
      reduceHeader();
      reducePage();
   }
}

class UrlRequest {
    public String HTMLtoDisplay = "";
    URLDesign urlDesign;
    public String getHTMLtoDisplay(){
        return HTMLtoDisplay;
    }

    public String recieveHTMLtoDisplay(String url){
        return recieveHTMLtoDisplay(url, 80);
    }

    public String recieveHTMLtoDisplay(String urlHost, int port){
        Socket connect = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String attemptToGetText = "";
        urlDesign = new URLDesign(urlHost);
        attemptToGetText = urlDesign.requestHTML();
        urlHost = urlDesign.getPageRequest();
        try {
            connect = new Socket(urlHost, port);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        if (connect != null && connect.isConnected()) {
            try {
                printWriter = new PrintWriter(connect.getOutputStream());
            } catch (IOException ex){
                System.out.println("PrinterWriter could not be made!");
            }

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            } catch (IOException ex){
                System.out.println("BufferredReader could not be made!");
            }
            printWriter.print(attemptToGetText);
            printWriter.flush();
            String pageLine = "";
            try {
                while ((pageLine = bufferedReader.readLine()) != null){
                    HTMLtoDisplay += pageLine;
                }
            } catch (IOException ex){
                System.out.println("cannot get the next line from the buffered reader");
            }
        }
        return HTMLtoDisplay;
    }
}

class URLDesign{
    public String urlInput = "";
    public URL urlToUse;
    private String pageRequest = "";
    private String URLRequest = "";
    URLDesign(String url){
        this.urlInput = url;
        getURL();
    }
    public String getPageRequest() {
        return URLRequest;
    }
    public String requestHTML(){
        String pages = "";
        pages += "GET "+pageRequest+" "+"HTTP/1.1"+"\n";
        pages += "Host: "+URLRequest+" \n \n";
        return pages;
    }
    public void getURL(){
        try {
            if(!(urlInput.startsWith("http://"))){
                urlInput = "http://" + urlInput;
            }
            urlToUse = new URL(urlInput);
            pageRequest = ( (pageRequest = urlToUse.getFile().trim()).equals("")) ? "/" : pageRequest;
            System.out.println("Requesting: '"+pageRequest+"'");
            URLRequest = urlToUse.getHost();
        } catch (MalformedURLException ex){
            System.out.println("Malformed URL exception");
        }
    }
    public void getURLInput(String urlInput) {
        this.urlInput = urlInput;
        System.out.println(urlInput);
    }
}
