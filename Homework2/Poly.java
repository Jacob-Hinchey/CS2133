import java.util.*;

public class Poly {

	public static void main(String[] args) {
		int[] firstTest = {9, 16, 20, -1};
    int[] secondTest = {0, 1, 2, 0, 3, 4};
    int[] thirdTest = {3, 9, 1, 4, 3, 2, 6, 17};
		Poly polyTestOne = new Poly(firstTest);
    Poly polyTestTwo = new Poly(secondTest);
		Poly polyTestThree = new Poly(thirdTest);
    Poly polyTestFour = polyTestOne.add(polyTestTwo);
    Poly polyTestFive = polyTestOne.add(polyTestThree);
    String suspect, correct;
    double evaluateCalc;
    suspect = polyTestOne.toString();
    correct = "-1x^3+20x^2+16x+9";

    if(suspect.equals(correct)){
      System.out.println("Test Passed!" );
    }
    else{
       System.out.println("Test Failed!" );
     }
     suspect = polyTestTwo.toString();
     correct = "4x^5+3x^4+2x^2+1x";
     if(suspect.equals(correct)){
       System.out.println("Test Passed!" );
     }
     else{
        System.out.println("Test Failed!" );
     }
     suspect = polyTestFour.toString();
     correct = "4x^5+3x^4+22x^2+17x+9";
     if(suspect.equals(correct)){
       System.out.println("Test Passed!" );
     }
     else{
       System.out.println("Test Failed!" );
     }
     suspect = polyTestFive.toString();
     correct = "17x^7+6x^6+2x^5+3x^4+4x^3+21x^2+25x+12";
     if(suspect.equals(correct)){
       System.out.println("Test Passed!" );
     }
     else{
       System.out.println("Test Failed!" );
     }
     evaluateCalc = polyTestOne.evaluate(5);
     if(evaluateCalc == 464){
       System.out.println("Test Passed!" );
     }
     else{
       System.out.println("Test Failed!" );
     }
     evaluateCalc = polyTestTwo.evaluate(7);
     if(evaluateCalc == 74536){
       System.out.println("Test Passed!" );
     }
     else{
       System.out.println("Test Failed!" );
     }
	}
  public int[] intForCoefficient;
  //Create a int to use in for loops including the amoung of ints
	public Poly(int[] intForCoefficient){
		this.intForCoefficient = intForCoefficient;
	}

	public int degree(){
		return this.intForCoefficient.length - 1;
	}

	public String toString(){
		String Polynom ="";
    int inputMinusOne = this.numberOfInputs() -1;
		for(int i = inputMinusOne; i >= 0; i--){
      if (this.intForCoefficient[i] == 0) {
        //Do not add an 'x' becouse it is multipled by zero
				continue;
			}
      else if(i == 0){
        if(this.intForCoefficient[i] > 0) {
					Polynom+= "+" + this.intForCoefficient[i];
				}
        else {
					// - is attached to number
					Polynom += this.intForCoefficient[i];
				}
      }
			else if(i == inputMinusOne){
				Polynom+= this.intForCoefficient[i] + "x^" + i;
			}
      else if(i == 1){
				if(this.intForCoefficient[i] == 0){
					//Do not add an 'x' becouse it is multipled by zero
					continue;
				}
        else if(this.intForCoefficient[i] > 0){
					Polynom += "+" + this.intForCoefficient[i] + "x";
				}
        else {
					// - is attached to number
					Polynom += this.intForCoefficient[i] + "x";
				}
			}
      else if(this.intForCoefficient[i] == 1){
				Polynom+= "+x^" + i;
			}
      else {
				if(this.intForCoefficient[i] > 0){
					Polynom+="+" + this.intForCoefficient[i] + "x^" + i;
				}
        else {
					Polynom+=this.intForCoefficient[i] + "x^" + i;
				}
			}
		}
		return Polynom;
	}

  public int numberOfInputs(){
		return this.intForCoefficient.length;
	}

  public Poly add(Poly a){
		//Must be set equal in loop to avoid exception.
		int[] added;
    Poly sum;
    if(this.numberOfInputs() > a.numberOfInputs()){
			added = new int[this.numberOfInputs()];
			for(int i = 0; i < a.numberOfInputs() - 1; i++){
				added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
			}
			for(int i = a.numberOfInputs() - 1; i < this.numberOfInputs(); i++){
				added[i] = this.intForCoefficient[i];
			}
			sum = new Poly(added);
		}
    else if(this.numberOfInputs() < a.numberOfInputs()){
			added = new int[a.numberOfInputs()];
			for(int i = 0; i < this.numberOfInputs() - 1; i++){
				added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
			}
			for(int i = this.numberOfInputs() - 1; i < a.numberOfInputs(); i++){
				added[i] = a.intForCoefficient[i];
			}
			sum = new Poly(added);
		}
    else {
			added = new int[a.numberOfInputs()];
			for(int i = 0; i < a.numberOfInputs() - 1; i++){
				added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
			}
			sum = new Poly(added);
		}
    return sum;
	}

	public double evaluate(double x){
		double answer = 0;
		for(int i = 0; i < this.numberOfInputs(); i++){
      double temp = intForCoefficient[i];
			answer = answer + temp * Math.pow(x, i);
		}
		return answer;
	}
}
