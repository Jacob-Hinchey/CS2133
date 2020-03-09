import java.util.*;
//Because the bubble sort runs out of time faster than the merge it helps to show that O(n^2) is much slower 
//than O(nlogn) which is the merge
public class Sorting {
    public static int n = 1;
    public static int mult = 10;
    public static double begin = 0;
    public static double finish = 0;
    public static double bubbleTime = 0;
    public static double mergeTime = 0;
    public static boolean bubbleValid = true;

    public static void main (String[] args){
      double[] arrayX = new double[n];
      double[] arrayY = new double[n];
        while (true){
            n = n*mult;
            try{
              arrayX = new double[n];
              arrayY = new double[n];
            }
            catch(Exception ex){
              ex.printStackTrace();
            }
            catch(OutOfMemoryError ex){
              System.out.println("Out of Memory!");
              System.exit(0);
            }
            for(int i = 0; i < n; i++){
                double hold = Math.random();
                arrayX[i] = hold;
                arrayY[i] = hold;
            }
            double begin;
            double finish;
            begin = System.currentTimeMillis();
            mergeSort(arrayX);
            finish = System.currentTimeMillis();
            mergeTime = (finish - begin)/1000;
            if(mergeTime < 20){
              System.out.printf("Merge Sort finished in: %.2f seconds \n", mergeTime);
            }
            else{
              System.out.println("Merge sort took longer than 20 seconds");
            }
            begin = 0;
            finish = 0;
            if(bubbleValid){
              bubbleSort(arrayY);
              if(bubbleValid){
                System.out.printf("Bubble Sort finished in: %.2f seconds \n", bubbleTime);
              }
            }
            System.out.println();
        }
    }

    public static void bubbleSort(double[] sortArr){
        begin = System.currentTimeMillis();
        boolean sorted = true;
        while (sorted){
            if (System.currentTimeMillis() - begin > 20000) {
                System.out.println("Bubble sort took longer than 20 seconds");
                bubbleValid = false;
                break;
            }
            sorted = false;
            for(int i = 0; i < sortArr.length - 1; i++){
                if(sortArr[i] > sortArr[i + 1]){
                    double hold = sortArr[i];
                    sortArr[i] = sortArr[i + 1];
                    sortArr[i + 1] = hold;
                    sorted = true;
                }
            }
        }
        finish = System.currentTimeMillis();
        bubbleTime = (finish - begin)/1000;
    }

    public static double[] mergeTest(double[] left, double[] right){
        double[] test = new double[left.length + right.length];
        int leftM = 0;
        int rightM = 0;
        for(int i = 0; i < test.length; i++){
            if(rightM >= right.length || (leftM < left.length && left[leftM] <= right[rightM])){
                test[i] = left[leftM];
                leftM++;
            }
            else {
                test[i] = right[rightM];
                rightM++;
            }
        }
        return test;
    }

    public static double[] mergeSort(double[] sortArr){
        double[] test = null;

        if (sortArr.length <= 1){
            return sortArr;
        }

        int arrayHalf = sortArr.length / 2;
        double[] left = new double[arrayHalf];
        double[] right = new double[sortArr.length - arrayHalf];

        System.arraycopy(sortArr, 0, left, 0, arrayHalf);
        System.arraycopy(sortArr, arrayHalf, right, 0, (sortArr.length - arrayHalf));

        left = mergeSort(left);
        right = mergeSort(right);

        test = mergeTest(left, right);

        return test;
    }
}
