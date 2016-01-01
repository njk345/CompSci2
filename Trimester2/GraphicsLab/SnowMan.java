/**
 * This Class consists of drawing snowmen of different sizes and turtles, which can trace their way across the screen avoiding the snowmen.
 * 
 * Author: Nick Keirstead
 * Began: 1/10/15
 * Finished: 1/11/15
 */
import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Color;

public class SnowMan extends GraphicsProgram {
    public void init () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        
        setBackground(Color.cyan);
        drawSnowMan(scrWidth/2,scrHeight/2 - 20,30,3);
        drawThreeSnowmen();
        
        GTurtle nick = drawTurtle(0,120,0.9);
        add(nick);
        plotCourse(nick,true);
        
        GTurtle joe = drawTurtle(scrWidth,scrHeight-30,0.9);
        add(joe);
        plotCourse(joe,false);
    }
    /*
     * Final Step:  Embellish the snowman in any way you wish
     * just demonstrate usage of a GObject!  Check the API - how about a turtle?
     */
    public void drawSnowMan(double x, double y, double size, int parts) {
        GRect tophat = drawRect(x,y,size/4,size/4);
        double x2 = x - (size/2 - size/8);//shift to align with center of tophat
        double y2 = y + size/4;//initial downward shift
        x2 = Math.round(x2);
        y2 = Math.round(y2);
        // make them whole numbers so plotCourse will detect obstacles when shifting position by 1
        for (int i = parts - 1; i >= 0; i--) {//creates any number of body sections
            if (i == 0) {
                add(drawOval(x2,y2,size,size));
            }
            else {
                add(drawOval(x2,(y2 + size * i),size,size)); 
            }
        }
        add(tophat);
        //draw snowman bottom to top so hat is topmost GObject at point where body and hat meet --> so plotCourse traces hat too
    }
    public void drawThreeSnowmen() {
        double scrWidth = getWidth();
        drawSnowMan(scrWidth/3,100,10,3);
        drawSnowMan(scrWidth/2,100,25,3);
        drawSnowMan(2*(scrWidth/3),100,50,3);
    }
    public void plotCourse (GTurtle turtle, boolean leftToRight) {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        double step = 0.005; //smaller the step, more precise the oval tracing
        turtle.penDown();//start tracing path
        turtle.setColor(Color.black);//pen is green unless obstacle encountered during trip

        if (leftToRight) {
            while (turtle.getX() <= scrWidth && turtle.getY() > 0) {
                if (getElementAt((turtle.getX() + step),turtle.getY()) != null) {//if object blocking to right
                    turtle.setColor(Color.red);
                    while (getElementAt((turtle.getX() + step),turtle.getY()) != null && turtle.getY() < scrHeight) {//while blocked to the right and above bottom of screen
                        if (getElementAt(turtle.getX(),turtle.getY()+step) != null) {//if object blocking below too
                            while (getElementAt(turtle.getX(),turtle.getY()+step) != null && turtle.getX() > 0) {//while blocked below and right of left screen edge
                                turtle.setLocation(turtle.getX()-step,turtle.getY());//move back
                            }
                        } 
                        turtle.setLocation(turtle.getX(),turtle.getY()+step);//move down
                    }
                }
                //turtle is now clear to proceed right
                while (getElementAt((turtle.getX() + step),turtle.getY()) == null && turtle.getX() <= scrWidth) {
                    turtle.forward(step);//move right across screen until path blocked or right edge of screen reached
                }
            }
        }
        else {
            turtle.left(180); //face turtle left
            while (turtle.getX() >= 0 && turtle.getY() > 0) {
                if (getElementAt((turtle.getX() - step),turtle.getY()) != null) {//if object blocking to right
                    turtle.setColor(Color.red);
                    while (getElementAt((turtle.getX() - step),turtle.getY()) != null && turtle.getY() < scrHeight) {//while blocked to the left and above bottom of screen
                        if (getElementAt(turtle.getX(),turtle.getY()+step) != null) {//if object blocking below too
                            while (getElementAt(turtle.getX(),turtle.getY()+step) != null && turtle.getX() < scrWidth) {//while blocked below and left of left screen edge
                                turtle.setLocation(turtle.getX() + step,turtle.getY());//move back
                            }
                        } 
                        turtle.setLocation(turtle.getX(),turtle.getY()+step);//move down
                    }
                }
                //turtle is now clear to proceed left
                while (getElementAt((turtle.getX() - step),turtle.getY()) == null && turtle.getX() >= 0) {
                    turtle.forward(step);//move left across screen until path blocked or left edge of screen reached
                }
            }
        }
        turtle.penUp();
        
    }
    public GRect drawRect(double x, double y, double w, double h) {
        GRect rect = new GRect (x,y,w,h);
        rect.setFilled(true);
        rect.setFillColor(Color.black);
        return rect;
    } 
    public GOval drawOval(double x, double y, double w, double h) {
        GOval oval = new GOval (x,y,w,h);
        oval.setFilled(true);
        oval.setFillColor(Color.white);
        return oval;
    }
    public GTurtle drawTurtle(double x, double y, double speed) {
        GTurtle turtle = new GTurtle (x,y);
        turtle.setSpeed(speed);
        return turtle;
    }
}
