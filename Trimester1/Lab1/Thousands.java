/**
 * This program takes in an integer between 1000 and 99999, and returns it with commas separating place
 * values as is appopriate.
 * 
 * @author Nick Keirstead 
 * @version 9/17/14
 */
import java.util.Scanner;

public class Thousands
{
    public static void main (String [] args) {
        System.out.println("\f");
        Scanner input = new Scanner (System.in);
        
        System.out.println("Enter an integer >= 1000 & <= 99999");
        int num = input.nextInt();
        
        int beforeDecimal = num / 1000;
        int lastDig = num % 10;
        num /= 10;
        int secondLast = num % 10;
        num /= 10;
        int thirdLast = num % 10;
        
        //doing it digit by digit this way maintains leading zeroes
        
        System.out.print(beforeDecimal);
        System.out.print(",");
        System.out.print(thirdLast);
        System.out.print(secondLast);
        System.out.print(lastDig);
    }
}
