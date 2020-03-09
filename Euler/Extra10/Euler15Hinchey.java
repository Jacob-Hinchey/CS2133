import java.util.*;

public class Euler15Hinchey{
    private static long[][] grid = new long[21][21];

    public static void main(String[] args) {
        for(int i = 0; i < 21; i++) {
            for(int j = 0; j < 21; j++) {
                grid[i][j] = -1;
            }
        }
        System.out.println(find(21-1, 21-1));
    }

    public static boolean passed(int t1, int t2) {
        if(t1 < 0 || t2 < 0 || t1 >= 21 || t2 >= 21){
          return false;
        }
        return true;
    }

    public static long find(int t1, int t2) {
        if(!passed(t1, t2)){
          return 0;
        }
        if(t1 == 0 && t2 == 0){
          return 1;
        }
        if(grid[t1][t2] != -1){
          return grid[t1][t2];
        }
        return (grid[t1][t2]=find(t1-1, t2)+find(t1, t2-1));
    }
}
