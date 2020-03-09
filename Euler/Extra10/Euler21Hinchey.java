import java.util.*;
import java.io.*;

public class Euler21Hinchey {
	public static int test(int n) {
		int rtrn = 0;
		for(int i=2; i<=Math.sqrt(n); i++){
			if(n%i == 0){
				if(i == n/i){
					rtrn += i;
				}
				else{
					rtrn += i + n/i;
				}
			}
		}
		rtrn++;
		return rtrn;
	}

	public static void main(String[] args){
		int hold;
		int answer = 0;
		for(int i = 1; i<10000; i++){
			hold = test(i);
			if (hold>i && test(hold)==i) answer += i + hold;
		}
		System.out.println(answer);
	}
}
