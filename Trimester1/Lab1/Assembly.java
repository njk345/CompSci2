/**
 * Assembly class takes in three lines of input, each one an instruction to subtract a given integer from
 * the accumulator (begins at 0). Displays mathematical equivalent in one line of the 3 inputs, 
 * displays final value stored in the accumulator.
 * 
 * @author Nick Keirstead
 * @version 9/16/14
 */
import java.util.Scanner;

public class Assembly
{
   public static void main (String[] args) {
       System.out.println("\f");
       Scanner input = new Scanner (System.in);
       
       System.out.println("Enter one SUB expression");
       String ignore1 = input.next(); //don't need to deal w/ operator yet, but get it out of way
       int num1 = input.nextInt();
       
       System.out.println("Enter second SUB expression");
       String ignore2 = input.next();
       int num2 = input.nextInt();
       
       System.out.println("Enter third SUB expression");
       String ignore3 = input.next();
       int num3 = input.nextInt();
       
       int accumulator = 0;
       
       System.out.println("The expression is " + accumulator + " - " + num1 + " - " + num2 + " - " + num3);
       
       accumulator -= num1;
       accumulator -= num2;
       accumulator -= num3;
       
       System.out.println("The result is " + accumulator);
    }
}
