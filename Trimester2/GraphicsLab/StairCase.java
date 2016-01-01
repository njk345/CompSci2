
/**
 * This class draws a staircase with number of steps desired by the user. It can construct the staircase using nested for loops and
 * methods, or with recursion.
 * 
 * Nick Keirstead
 * Started: 1/5/15
 * Finished: 1/13/15
 */

import java.util.Scanner;
public class StairCase
{
    void runProgram () {
        Scanner input = new Scanner(System.in);
        System.out.println("How many steps would you like in staircase?");
        int steps = input.nextInt();//make accessile throughout
        drawNSteps(steps);
        //draw3Steps();
        //recursiveStairs(steps,0);
        System.out.println("\n");
        System.out.println("By Nick Keirstead, January 2015");
    }
    void drawTread (boolean endOrNot) {
        String tread = "+----";
        if (endOrNot) {
            tread += "+";
        }
        System.out.print(tread);
    }
    void drawRiser (boolean endOrNot) {
        String riser = "|    "; //bar with 4 spaces
        if (endOrNot) {
            riser += "|";
        }
        System.out.print(riser);
    }
    void drawIndent () {
        System.out.print("     ");
    }
    void draw3Steps () {
        drawNSteps(3);
    }
    void drawNSteps (int steps) {
        for (int level = 1; level <= steps; level++) {
            for (int i = steps; i > level; i--) {//draw as many blank spaces as needed per step
                drawIndent();
            }
            for (int y = 1; y <= level; y++) {//draw as many treads as needed per step
                if (y == level) {//last time through, end step with '+'
                    drawTread(true);
                }
                else {//otherwise not last step, end without '+'
                    drawTread(false);
                }
            }
            System.out.print("\n"); //next line
            for (int i = steps; i > level; i--) {//draw blank spaces once again for risers
                drawIndent();
            }
            for (int y = 1; y <= level; y++) {//draw necessary number risers based on level of staircase
                if (y == level) {
                    drawRiser(true);//end last riser with '|'
                }
                else {
                    drawRiser(false);//otherwise end with spaces
                }
            }
            System.out.print("\n");
        }
        for (int i = 1; i <= steps; i++) {//draw extra step layer at bottom
            if (i == steps) {
                drawTread(true); 
            }
            else {
                drawTread(false);
            }
        }
    }
    void recursiveStairs (int steps,int level) {//pretty ugly function, but recursive at the top level at least
        //level is current level of stairs being constructed --> pass in 0 at start
        if (level == 1 + steps) {//once level after end reached
            for (int i = 1; i <= steps; i++) {//draw extra step layer at bottom
                if (i == steps) {
                    drawTread(true); 
                }
                else {
                    drawTread(false);
                }
            }
        }
        else if (level <= steps) {//not done yet
            for (int i = steps; i > level; i--) {//draw as many blank spaces as needed per step
                drawIndent();
            }
            for (int y = 1; y <= level; y++) {//draw as many treads as needed per step
                if (y == level) {//last time through, end step with '+'
                    drawTread(true);
                }
                else {//otherwise not last step, end without '+'
                    drawTread(false);
                }
            }
            System.out.print("\n"); //next line
            for (int i = steps; i > level; i--) {//draw blank spaces once again for risers
                drawIndent();
            }
            for (int y = 1; y <= level; y++) {//draw necessary number risers based on level of staircase
                if (y == level) {
                    drawRiser(true);//end last riser with '|'
                }
                else {
                    drawRiser(false);//otherwise end with spaces
                }
            }
            System.out.print("\n");
            recursiveStairs(steps,level+1);
        }
    }
}