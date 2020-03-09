public class Euler4Hinchey{

  public static void main(String[] args){
    int highestAllowed = 100000;
      for (int i = 999 ; i >= 100 ; i--){
          for (int j = 999 ; j >= 100 ; j-- ){
              int palendromeNum = i * j;
              if ( highestAllowed < palendromeNum && palindromeTest(palendromeNum) ) {
                  highestAllowed = palendromeNum;
              }
          }
      }
      System.out.println(highestAllowed);
  }

  public static boolean palindromeTest(int input){
      int inverse = 0;
      int toTest = input;
      boolean palin = false;
      while (toTest > 0){
          inverse = 10 * inverse + toTest % 10;
          toTest /= 10;
      }
      if(input == inverse){
        palin = true;
      }
      return palin;
  }
}
