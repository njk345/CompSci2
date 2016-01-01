
/**
 * Write a description of class Triangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Triangle
{
  public static void main (String[] args) {
      int height = 7;
      int base = 5;
      
      double area = (height * base)/2;
      double hypotenuse = Math.sqrt(74);
      double perimeter = height + base + hypotenuse;
      
      System.out.println("The area is " + area + " and the perimeter is " + perimeter + ", or simply " + Math.ceil(perimeter) + ".");
    }
}
