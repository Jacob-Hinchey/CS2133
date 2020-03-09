//Euler Problem 6
//Sum Square Difference

import java.util.*;

public class Euler6Hinchey{

  public static void main(String[] args){

    double sumOfSquares, squareOfSum, answer;
    sumOfSquares = 0;
    squareOfSum = 0;

    for(int i = 1; i <= 100; i++){
      sumOfSquares = sumOfSquares + (Math.pow(i, 2));
      squareOfSum = squareOfSum + i;
      if(i == 100){
        squareOfSum = Math.pow(squareOfSum, 2);
        answer = squareOfSum - sumOfSquares;
        System.out.printf("%8.0f\n", answer);
      }
    }
  }
}
