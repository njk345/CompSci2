
/**
 * Write a description of class challengeTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.lang.StringBuilder;

public class challengeTwo
{
    public static void main(String[] args) {
        System.out.println("swole:");
        Scanner input = new Scanner(System.in);
        String x = input.nextLine();
        for (int i = 0; i < x.length(); i++) {
            x = x.substring(1,x.length() - i)
            + x.substring(0, 1)
            + x.substring(x.length() - i, x.length());
        }
        System.out.println(x);
    }
}
