public class Euler3Hinchey{

  public static void main(String args[]){
      double maximum=600851475143L;
      for(int i = 2;i < maximum ;i++){
          while(maximum % i==0){
              maximum=maximum/i;
          }
      }
      System.out.println(maximum);
  }
}
