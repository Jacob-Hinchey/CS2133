public class Euler14Hinchey {

    private static long val = 0;
    private static long max = 0;
    private static long nums = 0;
    public static void test(long num, long index, long begin){
        if(num == 1){
            val = index;
            if(max < val){
                max = val;
                nums = begin;
            }
        }
        else{
            if(num % 2 == 0){
                test(num / 2, index + 1, begin);
            }
            if(num % 2 == 1){
                test((num * 3) + 1, index + 1, begin);
            }
        }
    }

    public static void main(String[] args){
        for(int i = 1; i < 1000000; i++){
            test(i, 1, i);
        }
        System.out.println(nums);
    }
}
