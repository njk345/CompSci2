
/**
 * Write a description of class VariableTypes here.
 * 
 * - examples of declaring variables of different types
 * - use of arithmatic operators
 * - example of using String
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VariableTypes
{
    public static void main (String [] args) {
        int age = 5;
        char initial = 'j'; //for single characters (takes up less bits than a string)
        double gpa = 3.5; //for decimal point-containing numbers, a double is used
        int product = 3*4;
        int bigInt = Integer.MAX_VALUE + 1;
        
        String name = "Nick";
        System.out.println(name + " " + age);
        System.out.println(bigInt);
        
    }
}
