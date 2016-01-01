import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

/**
 * Write a description of class PolygonPlay here.
 * 
 * @author Nick Keirstead
 * @version 2/18/15
 */
public class PolygonPlay extends GraphicsProgram
{
    public static final double sideLen = 40;
    GPoint [] octa = new GPoint [8];
    
    public void init () {
        GPoint [] pts = new GPoint [3];
        pts[0] = new GPoint (500,100);
        pts[1] = new GPoint (480,140);
        pts[2] = new GPoint (520,140);
        GPolygon tri = new GPolygon (pts);
        tri.setFilled(true);
        tri.setFillColor(Color.yellow);
        add(tri);
        
        GTurtle octaTracer = new GTurtle (300,100);
        octaTracer.setVisible(true);
        octaTracer.setColor(Color.black);
        octaTracer.setSpeed(0.25);
        octaTracer.setSize(5);
        add(octaTracer);
        octaTracer.penDown();
        for (int i = 0; i < 8; i++) {
            octaTracer.forward(sideLen);
            octaTracer.right(45);
            octa[i] = octaTracer.getLocation();
        }
        octaTracer.penUp();
        remove(octaTracer);
        GPolygon stopsign = new GPolygon (octa);
        stopsign.setFilled(true);
        stopsign.setFillColor(Color.red);
        add(stopsign);
    }
    public void run () {
        while (true) {
            do {
                stopsign.move(0,2);
                wait(30);
            }
            while (
        }
    }
}
