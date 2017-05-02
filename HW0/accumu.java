public class accumu{
    public static void main(String[] args) {
        int x = 0;
        int accum = 0;
        while (x < 10) {
             accum = accum + x;
            System.out.print(accum +" ");
            x = x + 1;
        }
    }
}