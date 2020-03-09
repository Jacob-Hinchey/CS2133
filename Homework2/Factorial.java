
public class Factorial{

  public static long calculate(long n){
    long factCalc = n;
    if (n == 0){
      factCalc = 1;
      return 1;
    }
    else if(n>0 && n>20){
      System.out.println("Error. The number is negitive or too large!");
    }
    else{
      for(int i = (int)n; i>1; i--){
        factCalc = factCalc*(i-1);
      }
    }
    return factCalc;
  }

  public static void main(String[] args){
    long answerOne, answerTwo;
    answerOne = calculate(0);
    answerTwo = calculate(5);
    if(answerOne == 1){
      System.out.println("Factorial.calculate(0) returned 1.  Test passed!");
    }
    else{
      System.out.println("Factorial.calculate(0) did not return 1.  Test failed!");
    }
    if(answerTwo == 120){
      System.out.println("Factorial.calculate(5) returned 120.  Test passed!");
    }
    else{
      System.out.println("Factorial.calculate(5) did not return 120.  Test failed!");
    }

  }
}
