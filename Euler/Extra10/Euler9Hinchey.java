public class Euler9Hinchey{
  public static void main(String[] args){
    double c;
    int bottom;
    long answer;
    for (int a = 1; a <=500; a++){
      for (int b = a+1; b <= 500; b++){
        c = Math.sqrt((a*a)+(b*b));
        bottom = (int)c;
        if(c - bottom == 0 && a+b+c == 1000){
          answer = (long)a*(long)b*(long)c;
          System.out.println(answer);
        }
        else{
          continue;
        }
      }
    }
  }
}
