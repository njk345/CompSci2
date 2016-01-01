
/** This program displays individual letters making up the word ASCII, each made
 * of ASCII characters. It waits between displaying the letters, as if it were
 * a flipbook flipping through pages.
 * 
 * 
 * @author Nick Keirstead 
 * @version 9/24/14
 */


public class FlipBook
{
    public static void main (String [] args) throws InterruptedException {
        //double slash required for backslashes that aren't escape chars
        String a = "     ___     \n" + 
                   "    /   \\    \n" +
                   "   /  ^  \\   \n" +
                   "  /  /_\\  \\  \n" + 
                   " /  _____  \\ \n" +
                   "/__/     \\__\\ \n";
                   
        String s = "     _______.\n" + 
                   "    /       |\n" + 
                   "   |   (----`\n" +
                   "    \\   \\    \n" +
                   ".----)   |   \n" +
                   "|_______/    \n";
                   
        String c = "  ______ \n" +
                   " /      |\n" +
                   "|  ,---'|\n" +
                   "|  |     \n" +
                   "|  `----.\n" +
                   " \\______|\n";
                   
        String i = " __ \n" +
                   "|  |\n" +
                   "|  |\n" +
                   "|  |\n" +
                   "|  |\n" +
                   "|__|\n";
                   
                   
        System.out.println(a);
        flip();
        System.out.println(s);
        flip();
        System.out.println(c);
        flip();
        System.out.println(i);
        flip();
        System.out.println(i);
        flip();
        
    }
    public static void flip () throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("\f");
        Thread.sleep(150);
    }
}
