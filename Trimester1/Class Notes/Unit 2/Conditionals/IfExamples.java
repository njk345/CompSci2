
/**
 * Write a description of class IfExamples here.
 * 
 * @author Nick Keirstead
 * @version 10/15/14
 */


public class IfExamples
{
    public static void main (String [] args) {
        int x = 30;
        int y = 50;
        
        if (x < y) {
            System.out.println(x + " is less than " + y);
        }
        else if (x == y) {
            System.out.println(x + " is equal to " + y);
        }
        else {
            System.out.println(x + " is greater than " + y);
        }
    }
}
