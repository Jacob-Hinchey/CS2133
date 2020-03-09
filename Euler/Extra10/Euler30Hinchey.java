public class Euler30Hinchey {
    public static void main (String[] args){
      int answer = 0;
    	for(int i = 2; i < 1000000; i++){
    	    String number = "" + i;
    	    int fifth = 0;
          for(int j = 0; j < number.length(); j++){
                fifth = fifth + (int)Math.pow(Integer.parseInt("" + number.charAt(j)), 5);
    	    }
    	    if(fifth == i){
                answer += fifth;
    	    }
    	}
    	System.out.println(answer);
    }
}
