import java.util.Scanner;

public class AllPrimeNumbersBetweenTwoNumbers{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();

        for(int n = a; n<=b; n++) {
            int count = 0;

            // try to divide number n by the factors till root n
            for (int div = 2; div * div <= n; div++) {
                if(n % div == 0) {
                    count ++;
                    break;
                }
            }

            if(count == 0) {
                System.out.println(n);
            }
        }
    }
}