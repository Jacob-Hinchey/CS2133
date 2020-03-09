public class Euler10Hinchey{
	
	public static void main(String[] args){
		long answer = 0;
		for(long i = 2; i < 2000000; i++){
			if (isPrime(i))
				answer += i;
		}
		System.out.println(answer);
	}

	public static boolean isPrime(long val){
		boolean primeStatus = true;
		for(long i = 3; i <= Math.sqrt(val); i += 2){
			if(val % i == 0){
				primeStatus = false;
				break;
			}
		}
		if((val % 2 != 0 && primeStatus && val > 2) || val == 2){
			return true;
		}
		else{
			return false;
		}
	}
}
