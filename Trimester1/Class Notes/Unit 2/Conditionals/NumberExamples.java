
/**
 * 
 * 
 * @author Nick Keirstead
 * @version 10/15/14
 */

import java.util.Scanner;
public class NumberExamples
{
    public static void main (String [] args) {
        System.out.println("\f");
        
        Scanner input = new Scanner (System.in);
        System.out.println("Enter 3 different integers");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        
        if (a == b || a == c || b == c) {
            System.out.println("These are not different integers! Run it again.");
        }
        else {
            int larger;
            (a > b)? larger = a: larger = b;
            int largest;
            (larger > c)? largest = larger: largest = c;
            
            System.out.println("Biggest of first two is " + larger);
            System.out.println("Biggest of all three is " + largest);
        }
    }
}
