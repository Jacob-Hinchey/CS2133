import java.math.*;

public class Euler20Hinchey{

    public static long addNum(BigInteger input){
        long rtrn = 0;
        char chrs[] = new char[1000];
        chrs = input.toString().toCharArray();
        for(int i = 0; i < chrs.length; i++){
            rtrn += chrs[i] - '0';
        }
        return rtrn;
    }

    public static BigInteger factorial(BigInteger n){
        BigInteger bigInt = new BigInteger("1");
        if(n.compareTo(bigInt) <= 0){
            return new BigInteger("1");
        }
        else{
            return n.multiply(factorial(n.subtract(bigInt)));
        }
    }

    public static void main (String[] args){
        System.out.println(addNum(factorial(new BigInteger("100"))));
    }
}
