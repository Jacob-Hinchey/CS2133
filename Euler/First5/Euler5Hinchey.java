public class Euler5Hinchey{

  public static void main(String[] args) {

		int testMultiple, counter;
		int numberToCheck = 0;
		counter = 1;
    testMultiple = 20;
		boolean breakBool = false;
    boolean foundLeast;
		while (!breakBool){
			numberToCheck = testMultiple * counter;
			foundLeast = true;
			while (foundLeast && testMultiple > 0){
				if (numberToCheck%testMultiple == 0){
					testMultiple -= 1;
				}
        else{
					foundLeast = false;
				}
			}
			if (testMultiple == 0){
				breakBool = true;
			}
      else{
				counter++;
				testMultiple = 20;
			}
		}
		System.out.println(numberToCheck);
	}
}
