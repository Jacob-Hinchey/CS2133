//Euler Problem 2
//Even Fibonacci Numbers
import java.util.*;

public class Euler2Hinchey{

  public static void main(String[] args){

    int userNum, firstNum, secondNum, fibonacci, evenAdding;
    firstNum = 0;
    secondNum = 1;
    evenAdding = 0;
    for (int i = 1; i >= 0; i++){
      fibonacci = firstNum + secondNum;
      firstNum = secondNum;
      secondNum = fibonacci;
      if(fibonacci %2 ==0 && fibonacci < 4000000){
        evenAdding = evenAdding + fibonacci;
      }
      else if(fibonacci % 2 == 1){
        continue;
      }
      else{
        System.out.println(evenAdding);
        break;
      }
    }
  }
}
