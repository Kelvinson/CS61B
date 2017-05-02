public class DrawTriangle{
   public static void drawTriangle(int N) {
     int i = 1;
      while(i<=N) {
       int j = 0 ;
         while(j<i){
         System.out.print("*");
            j++;
         }
         i++;
         System.out.println();
      }
   }
   
   public static void main(String[] args) {
      drawTriangle(10);
   }
}

