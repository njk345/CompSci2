/**
 * This class takes in two integers and returns a table of 7 different calculations performed on them.
 * 
 * @author Nick Keirstead 
 * @version 9/16/14
 */
import java.util.Scanner;

public class Calculations
{
   public static void main (String [] args) {
       System.out.println("\f");
       Scanner input = new Scanner (System.in);
       
       System.out.println("Enter two integers and i'll do some math with 'em");
       
       int a = input.nextInt();
       int b = input.nextInt();
       
       double sum = a + b;
       double difference = a - b;
       double product = a * b;
       double average = product/2;
       double distance = Math.abs(difference);
       double maximum = Math.max(a,b);
       double minimum = Math.min(a,b);
       
       //must add extra spaces to strings below to have column bars line up!
       String[] keys = {"sum     ","diff    ","prod    ","avrg    ","dist    ","max     ","min     "};
       double[] values = {sum,difference,product,average,distance,maximum,minimum};
       
       //let's loop through the two arrays to reduce tedious programming and System.out.println typing
       for (int i = 0; i < keys.length; i++) {
           System.out.println(keys[i] + " | " + values[i]);
           System.out.println("________ | ________");
        }
        
      
    }
}
