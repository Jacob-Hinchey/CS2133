import java.util.*;
public class Ramanujan{

  public static double ramanujanSolver(int userNum){

    double begin, answer, temp;
    answer =0;
    begin = ((2*Math.sqrt(2))/9801);
    for(int i = 0; i < userNum; i++){
      temp = (((Factorial.calculate(4 * i))*(1103+26390*i)))/
               (Math.pow((Factorial.calculate(i)), 4)*(Math.pow(396, (4*i))));
      answer = temp + answer;
    }
    answer = answer * begin;
    answer = 1/answer;
    return answer;
  }

  public static void main(String[] args){
    int userNum;
    double raman, percentDifference;
    userNum = Integer.parseInt(args[0]);
    raman = ramanujanSolver(userNum);
    percentDifference = (100 - (raman / Math.PI) * 100);
    System.out.println("PI according to Ramanujan series: " + raman);
    System.out.println("This differs from Java's value by " + percentDifference);
  }
}
