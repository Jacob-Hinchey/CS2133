/** This class creates polynomials for arrays of integers. It also evaluates
    for x and can add two polynomials together.
*/

public class PolyFunc extends Function {
  /** An array that holds to values to be used before each x in a polynomial.
  */
  public int[] intForCoefficient;
  /** Creates a object to hold the polynomials
      @param intForCoefficient Array containing coefficients
  */
  public PolyFunc(int[] intForCoefficient){
    this.intForCoefficient = intForCoefficient;
  }
  /** Gives the largest degree by subtracting the legnth of the arrray by 1
      @return The size of the array minus 1.
  */
  public int degree(){
    return this.intForCoefficient.length - 1;
  }
  /** Takes an array of numbers and makes them a polynomial and transfer them to a string.
      @return Polynum The completed polynomial string
  */
  public String toString(){
    String Polynom ="";
    int inputMinusOne = this.numberOfInputs() -1;
    for(int i = inputMinusOne; i >= 0; i--){
      if (this.intForCoefficient[i] == 0) {
        //Do not add an 'x' because it is multipled by zero
        continue;
      }
      else if(i == 0){
        if(this.intForCoefficient[i] > 0) {
          Polynom+= "+" + this.intForCoefficient[i];
        }
        else {
          Polynom += this.intForCoefficient[i];
        }
      }
      else if(i == inputMinusOne){
        Polynom+= this.intForCoefficient[i] + "x^" + i;
      }
      else if(i == 1){
        if(this.intForCoefficient[i] == 0){
          continue;
        }
        else if(this.intForCoefficient[i] > 0){
          Polynom += "+" + this.intForCoefficient[i] + "x";
        }
        else {
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
  /** Determines the size of the array that will be used for the polynomial
      @return The size of the array
  */
  public int numberOfInputs(){
    return this.intForCoefficient.length;
  }
  /** The function takes two polynomials and adds them together.
    @param a Polynomial that is added to another
    @return sum The sum of two polynomials
  */
  public PolyFunc add(PolyFunc a){
    PolyFunc sum;
    if(this.numberOfInputs() > a.numberOfInputs()){
      int[] added = new int[this.numberOfInputs()];
      for(int i = 0; i < a.numberOfInputs() - 1; i++){
        added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
      }
      for(int i = a.numberOfInputs() - 1; i < this.numberOfInputs(); i++){
        added[i] = this.intForCoefficient[i];
      }
      sum = new PolyFunc(added);
    }
    else if(this.numberOfInputs() < a.numberOfInputs()){
      int[] added = new int[a.numberOfInputs()];
      for(int i = 0; i < this.numberOfInputs() - 1; i++){
        added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
      }
      for(int i = this.numberOfInputs() - 1; i < a.numberOfInputs(); i++){
        added[i] = a.intForCoefficient[i];
      }
      sum = new PolyFunc(added);
    }
    else {
      int[] added = new int[a.numberOfInputs()];
      for(int i = 0; i < a.numberOfInputs() - 1; i++){
        added[i] = this.intForCoefficient[i] + a.intForCoefficient[i];
      }
      sum = new PolyFunc(added);
    }
    return sum;
  }
  /** Takes a polynomial and a double and solves by using the double as x.
      @param x The double to replace x
      @return answer The evaluated polynomial
  */
  public double evaluate(double x){
    double answer = 0;
    for(int i = 0; i < this.numberOfInputs(); i++){
      double temp = intForCoefficient[i];
      answer = answer + temp * Math.pow(x, i);
    }
    return answer;
  }
}
