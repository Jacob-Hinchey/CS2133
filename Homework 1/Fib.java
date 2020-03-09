import java.util.*;

public class Fib{

  public static void main(String[] args){

    int userNum, firstNum, secondNum, fibonacci;
    firstNum = 0;
    secondNum = 1;
    userNum = Integer.parseInt(args[0]);
    for (int i = 1; i >= 0; i++){

      fibonacci = firstNum + secondNum;
      firstNum = secondNum;
      secondNum = fibonacci;
      if (i == userNum - 1){
        System.out.println(fibonacci);
        break;
      }
    }
  }
}
