public class Euler12Hinchey{

      public static void main(String[] args) {
          int limit = 0;
          int condition = 0;
          boolean highest = false;
          while (highest != true){
              int count = 0;
              condition++;
              limit = (condition * (condition + 1)) / 2;
              for(int i = 1; i*i <= limit; i++) {
                  if(limit % i == 0){
                      count = (limit % i == i ? count + 1 : count + 2);
                      if(count > 500){
                          highest = true;
                          break;
                      }
                  }
              }
          }
          System.out.println(limit);
      }
  }
