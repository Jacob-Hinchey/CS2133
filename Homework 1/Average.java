import java.util.*;

public class Average
{
  public static void main(String args[])
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter a series of numbers. Enter a negitive number to quit.");
    int count = 0;
    double sum =0;
    double[] average = new double[1];
    for(int i=0; i>=0; i++)
    {
      average = new double[i+1];
      average[i] = scan.nextDouble();
      {
        if (average[i] < 1)
        {
          System.out.println("You entered " + count + " numbers averaging " + sum/count + ".");
          break;
        }
        sum = sum + average[i];
        count++;
      }
    }
  }
}
