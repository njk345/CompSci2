
/**
 * Write a description of class Factorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Factorial
{
    public static void main (String [] args) {
        System.out.println(fact(5));
    }
    static int fact (int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return n * fact(n-1);
        }
    }
}
