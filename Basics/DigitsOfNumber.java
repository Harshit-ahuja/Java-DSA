import java.util.Scanner;
import java.lang.Math;

/* Need to make use of the quotient approach instead of the remainder approach 
so that the digits are not printed in the reversed manner */
public class DigitsOfNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        // Calculating the count of digits
        int temp = n;
        int numberOfDigits = 0;
        while(temp != 0) {
            temp = temp / 10;
            numberOfDigits++;
        }

        // Calculating the divisor using the count of digits
        int div = (int)Math.pow(10, numberOfDigits - 1);

        /* The terminating condition of the loop should make use of the divisor 
        instead of the number to take into account the trailing zeroes */
        while(div != 0) {
            int quotient = n / div;
            System.out.println(quotient);

            n = n % div;
            div = div / 10;
        }
    }
}