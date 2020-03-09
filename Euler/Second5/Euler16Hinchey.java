import java.math.*;

public class Euler16Hinchey{

   public static void main(String[] args){
     BigInteger first = new BigInteger("1");
     BigInteger second = new BigInteger("2");
     for (int i = 0; i < 1000; i++){
       first=first.multiply(second);
     }
     int added=0;
     String toText=first.toString();
     for (int i = 0; i < toText.length(); i++){
       added+=Integer.parseInt(new Character(toText.charAt(i)).toString());
     }
     System.out.println(added);
   }
}
