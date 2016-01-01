
/**
 * Write a description of class IteratorArrayTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Iterator;
import acm.graphics.*;
import java.awt.Color;
import acm.program.*;

public class IteratorArrayTest extends GraphicsProgram
{
    @Override
    public void init () {
        ArrayList<String> array = new ArrayList<String>();
        GRect thing = new GRect(100,100,10,10);
        thing.setFilled(true);
        thing.setFillColor(Color.red);
        add(thing);
        pause(1000);
        remove(thing);
        pause(1000);
        remove(thing);
        
        for (int i = 0; i < 10; i++) {       
            array.add("a");
            array.add("b");
            array.add("c");
        }
        System.out.println(array);
        for (Iterator<String> i = array.iterator(); i.hasNext();) {
            if (i.next().equals("a")) {
                i.remove();
            }
        }
        System.out.println(array);
    }
}
