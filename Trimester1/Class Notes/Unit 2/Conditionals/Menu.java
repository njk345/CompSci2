
/**
 * Display menu of 3 desert choices - 1, 2, and 3. Ask the user to pick a choice and display what their
 * choice consists of. Throw an error if the choice is not valid.
 * 
 * @author Nick Keirstead
 * @version 10/15/14
 *
 */
import java.util.Scanner;

public class Menu
{
    public static void main (String [] args) {
        System.out.println("\f");
        
        Scanner input = new Scanner (System.in);
        
        System.out.println("Choice 1: fondue");
        System.out.println("Choice 2: pound cake");
        System.out.println("Choice 3: gelato");
        System.out.println("\n");
        System.out.println("Which desert choice would you like?");
        
        int choice = input.nextInt();
        
        switch (choice) {
            case 1:
            System.out.println("Enjoy your fondue!");
            break;
            case 2:
            System.out.println("Enjoy that pound cake!");
            break;
            case 3:
            System.out.println("Here's your gelato!");
            break;
            default:
            System.out.println("Not a valid choice, bro");
        }
        
    }
}
