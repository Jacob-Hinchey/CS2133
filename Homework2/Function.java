/** A command line tool to find a number between two other numbers
    and then find their root.
*/
public abstract class Function {
  /** Implemnets function that will have its root taken.
    @param x Number used in function
  */
	public abstract double evaluate(double x);
  /** Finds number between a and b that is within a amount guaranteed by recursion
      @param a First value
      @param b Second value
      @param epsilon Amount the answer if allowed to be off.
      @return The number between a and b within a vertain limit.
  */
	public double findRoot(double a, double b,  double epsilon) {
		double x = (a + b) / 2;
		if (Math.abs(a - x) < epsilon) {
			return x;
		}
    else if (evaluate(a) > 0 && evaluate(x) > 0){
			return findRoot(x, b, epsilon);
		}
    else if(evaluate(a) < 0 && evaluate(x) < 0){
      return findRoot(x, b, epsilon);
    }
    else {
			return findRoot(a, x, epsilon);
		}
	}

	public static void main(String[] args){
	  double epsilon = .00000001;
    int[] polyTestOne = {-3, 0, 1};
    int[] polyTestTwo = {-2, -1, 1};
    PolyFunc polyFuncTestOne = new PolyFunc(polyTestOne);
    PolyFunc polyFuncTestTwo = new PolyFunc(polyTestTwo);
		SinFunc sinTest = new SinFunc();
    CosFunc cosTest = new CosFunc();

    double toTest =sinTest.findRoot(3, 4, epsilon);
		if(toTest == 3.1415926590561867){
      System.out.println("Test Passed!");
    }
    else{
      System.out.println("Test Failed!");
    }
    toTest = cosTest.findRoot(1, 3, epsilon);
    if(toTest == 1.5707963332533836){
      System.out.println("Test Passed!");
    }
    else{
      System.out.println("Test Failed!");
    }
    toTest = polyFuncTestOne.findRoot(0, 15, epsilon);
    if(toTest == 1.732050811406225){
      System.out.println("Test Passed!");
    }
    else{
      System.out.println("Test Failed!");
    }
    toTest = polyFuncTestTwo.findRoot(0, 15, epsilon);
    if(toTest == 1.9999999995343387){
      System.out.println("Test Passed!");
    }
    else{
      System.out.println("Test Failed!");
    }
  }
}
