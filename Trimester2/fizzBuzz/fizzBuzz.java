
/**
 * Write a description of class fizzBuzz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fizzBuzz
{
    public static void main (String[] shit) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FIZZBUZZ");
            }
            else if (i% 3 == 0) {
                System.out.println("FIZZ");
            }
            else if (i% 5 == 0) {
                System.out.println("BUZZ");
            }
            else {
                System.out.println(i);
            }
        }
    }   
}
