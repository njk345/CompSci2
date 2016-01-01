
/**Program takes in an amount of dollars due from the customer, how much the customer pays, and how
 * much change should be returned to the customer.
 * 
 * 
 * @author Nick Keirstead 
 * @version 9/23/14
 */

import java.util.Scanner;

public class Change
{
    public static void main (String[] args) {
        System.out.println("\f");
        Scanner input = new Scanner (System.in);
        
        System.out.println("Cost of the item (no $ sign needed):");
        double cost = input.nextDouble();
        System.out.println("Amount customer pays (must be >= cost; include two decimals):");
        double payed = input.nextDouble();
        double change1 = (payed - cost) * 100; //deal with it in cents
        
        int change = (int)change1; //cast as int so integer division possible
        
        int dollars = change / 100;
        change = change % 100;
        int quarters = change / 25;
        change = change % 25;
        int dimes = change / 10;
        change = change % 10;
        int nickels = change / 5;
        change = change % 5;
        int pennies = change / 1;
        
        System.out.println("Your change is: " + dollars + " dollars, " + quarters + " quarters, " + dimes + " dimes, "
        + nickels + " nickels, and " + pennies + " pennies.");
        
    }
}
