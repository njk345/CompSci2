/**
 * Takes in number of gallons in a tank, miles per gallon efficiency of car, and price of gas per gallon.
 * Returns how far car can travel on tank, cost per 100 miles driven.
 * 
 * @author Nick Keirstead
 * @version 9/15/14
 */

import java.util.Scanner;

public class Gallons
{
   public static void main (String[] args) {
       System.out.println("\f");
       
       Scanner input = new Scanner(System.in);
       System.out.println("How many gallons in your tank right now?");
       double gallons = input.nextDouble(); //allowing for non-integer answers
       System.out.println("How many miles per gallon do you get?");
       double mpg = input.nextDouble();
       System.out.println("How much money for a gallon?");
       double priceGall = input.nextDouble();
       
       double dist = gallons * mpg;
       double costPerMile = priceGall / mpg;
       double costPer100 = costPerMile * 100;  
       
       System.out.println("Your car can travel " + dist + " miles on its current tank. It will cost $"
       + costPer100 + " to drive 100 miles.");
       
    }
}
