import java.math.*;
import java.util.*;

public class Euler25Hinchey{
    public static void main(String[] args){
        ArrayList<BigInteger> fib = new ArrayList<>();
        fib.add(BigInteger.valueOf(1));
        fib.add(BigInteger.valueOf(1));
        for(int i = 1; (fib.get(i).compareTo(BigInteger.TEN.pow(999))) == -1; i++){
            fib.add(fib.get(i).add(fib.get(i - 1)));
        }
        System.out.println(fib.size());
    }
}
