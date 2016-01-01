import java.util.Scanner;
/**
 * 
 * Examples using the java.util class Scanner
 * to read int, double, and string data from the console (one way of receiving input)
 * @author Nick Keirstead
 * @version (a version number or a date)
 */
public class InputExamples
{
    public static void main (String[] args) {
       
       System.out.println("Please enter your expression. Write operator first, put spaces between numbers and operator.");
       Scanner input = new Scanner (System.in);
       double num1 = input.nextDouble();
       String operator = input.next();
       double num2 = input.nextDouble();
       
       System.out.println("You entered " + num1 + " " + operator + " " + num2  + ", which equals " + (num1 + num2));
    }
    
    /*working on separate method to evaluate expression with any given operator using switch statement
     * public void eval (String statement) {
        switch (sign) {
            case "+":
            
        }
    }*/
}
