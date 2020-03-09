public class Euler19Hinchey{

    public static void main(String[] args){
        int answer = 0;
        for (int i = 1901; i <= 2000; i++){
            for (int j = 1; j <= 12; j++){
                if(calenderCalc(1, j, i) == 1){
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static int calenderCalc(int day, int month, int year) {
        if(month == 1){
            month = 13;
            year--;
        }
        if(month == 2){
            month = 14;
            year--;
        }
        int d = day;
        int m = month;
        int y1 = year % 100;
        int y2 = year / 100;
        int rtrn = d + 13*(m+1)/5 + y1 + y1/4 + y2/4 + 5*y2;
        rtrn %=7;
        return rtrn;
    }
}
