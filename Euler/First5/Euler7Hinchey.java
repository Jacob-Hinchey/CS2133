public class Euler7Hinchey{

  public static void main(String[] args) {

        int count, prime;
        boolean primeFound;
        prime = 0;
        count = 0;

        for (int i = 2; i < 10000000; i++) {
            primeFound = true;
            for (int j = 2; j < i; j++) {

                if (i % j == 0) {
                    primeFound = false;
                    break;
                }
            }
            if (primeFound) {
                prime = i;
                count++;
            }
            if (count == 10001) {
                break;
            }
        }
        System.out.println(prime);
    }
}
