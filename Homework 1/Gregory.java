import java.util.*;

public class Gregory{
  public static void main(String[] args){

  int userNum;
  double gregory, difference, solution;
  gregory = 0;
  userNum = Integer.parseInt(args[0]);
  for(int i = 1; i <= userNum; i++){

    solution = ((Math.pow(-1, (i + 1))) / ((2 * i) -1)) * 4;
    gregory = solution + gregory;
    if(i == userNum){
      System.out.println("PI according to Gregory series: " + gregory);
      difference = (java.lang.Math.abs((gregory-Math.PI)/Math.PI *100));
      System.out.println("This differs from Java's value by " + difference + " percent.");

      }
    }
  }
}
