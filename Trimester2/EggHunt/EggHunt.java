/**
 * This game is designed for kids in the 1st-2nd grade range, with basic reading skills and knowledge of the keyboard. (W,A,S,D keys)
 * In the game, called "Easter Egg Hunt," the player moves a bunny around the screen to collect eggs, until all eggs are collected.
 * If they come too close to one of the three other animals - a bear, tiger, or another tiger - they are brought back to starting point.
 * If they go into the water in the small pond, they are sent back to start as well.
 * The user can switch betweeen three moving speeds, "slow" "normal" "fast"
 * The user cannot go off the screen.
 * There is an instructional sequence that plays at the beginning and must be viewed.
 * There is a congratulatory screen (fireworks) that plays upon victory.
 * 
 * Each game, the positions of all eggs, animals, and the pond are randomly generated.
 * Methods are used to avoid overlapping of random positions
 * If overlapping occurs, new random positions are constantly tried until free space is found.
 * For eggs, if no free space found within set number of tries, the egg is deleted from game.
 * A random number of eggs is initialized in the above-mentioned way each game.
 * 
 * Future Ideas/Possible Additions:
 *      - Star Wars style opening instruction presentation
 *      - Since bg is in perspective, make field elements appear to shrink into distance as y values rise
 *      - More sounds!
 *      - More animals
 * 
 * 
 * Nick Keirstead
 * Started: 1/23/15
 * Finished: 2/9/15
 */
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import acm.util.SoundClip;
import acm.util.RandomGenerator;

public class EggHunt extends GraphicsProgram
{
    private RandomGenerator rand = RandomGenerator.getInstance();
    private int numEggs = rand.nextInt(20,50);
    private GRect tablet;
    private GLabel instructions;
    private GImage grass = new GImage("lawn.jpg");
    private GImage bunny = new GImage("bunny.png");
    private GImage pond = new GImage("pond.png");
    private GOval water;
    private GImage tiger = new GImage("tiger.png");
    private GImage tiger2 = new GImage("tiger.png");
    private GImage bear = new GImage("bear.png");
    private GImage fireworks = new GImage("fireworks.jpg");
    private GImage[] eggs = new GImage[numEggs];
    private GLabel score;
    private GRect scoreboard;
    private GLabel speedSwitch;
    private GRect speedHolder;
    private GLabel congrats;

    private int stepLen = 20;
    private int total = numEggs;
    private SoundClip loading = new SoundClip ("loading.wav");
    private SoundClip splash = new SoundClip ("splash.wav");
    private SoundClip hop = new SoundClip ("hop.wav");
    private SoundClip growl = new SoundClip ("growl.wav");
    private SoundClip roar = new SoundClip ("roar.wav");
    private SoundClip crack = new SoundClip ("crack.wav");
    private SoundClip victory = new SoundClip ("victory.wav");
    private String speedMode;
    private boolean gameOver = true;

    public void init () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        speedMode = "normal";
        placeBackground();
        addMouseListeners();
        readInstructions();
        waitMSecs(2000);

        gameOver = false;
        loading.setVolume(0.5);
        loading.play();
        spawnEggs();
        spawnPond();
        spawnBunny();
        spawnPredators();
        drawScoreboard();
        drawSpeedSwitch();
        eliminateOverlaps();

        addKeyListeners();
    }

    ////////////////////////////////////////////
    //DO-ER METHODS
    void placeBackground () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        grass.setLocation(0,0);
        grass.setSize(scrWidth,scrHeight);
        add(grass);
    }

    void readInstructions () {
        instructions = new GLabel("Get ready to play: Easter Egg Hunt!!",getWidth()/8,getHeight()/8);
        instructions.setFont(new Font("Serif",Font.BOLD,30));
        add(instructions);

        GLabel keepClicking = new GLabel ("(Click For Next Direction)",instructions.getX(),instructions.getY()+40);
        keepClicking.setFont(new Font("Serif",Font.BOLD,20));
        add(keepClicking);

        waitForClick();
        instructions.setLabel("To Win, Get All The Eggs With The Bunny!");
        waitForClick();
        instructions.setLabel("To Go Up or Down: Press W or S");
        waitForClick();
        instructions.setLabel("To Go Left or Right: Press A or D");
        waitForClick();
        instructions.setLabel("Watch Out For The Pond and Scary Animals!");
        waitForClick();
        instructions.setLabel("Have Fun! No Two Games Are The Same!");
        waitForClick();
        remove(instructions);
        remove(keepClicking);
        instructions.setLocation(0,-100);//dump off screen
    }

    void spawnEggs () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        for (int i = 0; i < eggs.length; i++) {
            eggs[i] = new GImage("egg.png");
            eggs[i].scale(0.05);
            assignSpot(eggs[i],true,eggs[i].getWidth(),scrWidth-eggs[i].getWidth(),35,scrHeight-eggs[i].getHeight());
            add(eggs[i]);
            waitMSecs(50);//little delay to sync with loading jingle
        }
    }

    void spawnPond () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        pond.scale(0.7);
        assignSpot(pond,false,pond.getWidth(),scrWidth-pond.getWidth(),35,scrHeight-pond.getHeight());
        add(pond);

        water = new GOval(pond.getX()+5,pond.getY()+20,pond.getWidth()-5,pond.getHeight()-40);
        water.setVisible(false);
        add(water); //the object that represents what the bunny will touch --> not an overly large sizing rectangle
    }

    void spawnPredators () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        tiger.scale(0.105);
        assignSpot(tiger,true,tiger.getWidth(),scrWidth-tiger.getWidth(),35,scrHeight-tiger.getHeight());
        add(tiger);

        bear.scale(0.09);
        assignSpot(bear,true,bear.getWidth(),scrWidth-bear.getWidth(),35,scrHeight-bear.getHeight());
        add(bear);

        tiger2.scale(0.105);
        assignSpot(tiger2,true,tiger2.getWidth(),scrWidth-tiger2.getWidth(),35,scrHeight-tiger2.getHeight());
        add(tiger2);
    }

    void eliminateOverlaps () {
        for (int i = 0; i < eggs.length; i++) {
            if (isBlocked(eggs[i],true) || insideAnything(eggs[i]) || isCramped(eggs[i],2)) {
                eggs[i].setLocation(0,-100); //out of sight and interaction
                total--;
                setScore(total);
            }
        }
    }

    void spawnBunny () {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        bunny.scale(0.5);
        bunny.setLocation(scrWidth-150,10);
        add(bunny);
    }

    void drawScoreboard () {
        scoreboard = new GRect(0,0,250,30);
        scoreboard.setFilled(true);
        scoreboard.setFillColor(Color.red);
        add(scoreboard);

        score = new GLabel ("Eggs To Collect: " + total,20,25);
        score.setFont(new Font("Serif",Font.BOLD,25));
        add(score);
    }

    void drawSpeedSwitch () {
        speedHolder = new GRect(getWidth()/2 - 100,0,150,30);
        speedHolder.setFilled(true);
        speedHolder.setFillColor(Color.green);
        add(speedHolder);

        speedSwitch = new GLabel ("Fast Speed!",speedHolder.getX() + 10,speedHolder.getY()+20);
        speedSwitch.setFont(new Font("Serif",Font.BOLD,20));
        add(speedSwitch);
    }

    ///////////////////////////////////////////////////////////////////////
    ///HELPER METHODS

    public void keyPressed (KeyEvent e) {
        if (!gameOver) {
            int code = e.getKeyCode();
            char key = e.getKeyChar();
            //System.out.println("Key code = " + code + " key char = " + key);
            double scrWidth = getWidth();
            double scrHeight = getHeight();

            if (key == 'w') {
                bunny.move(0,-stepLen);
                hop.setVolume(0.5);
                hop.play();
                if (isBlocked(bunny,false) || inWater(bunny)) {
                    react(bunny,'w');
                }
                else if (isOffScreen(bunny)) {
                    bunny.move(0,stepLen); //move it back to prevent going off screen
                }
            }
            else if (key == 's') {
                bunny.move(0,stepLen);
                hop.setVolume(0.5);
                hop.play();
                if (isBlocked(bunny,false) || inWater(bunny)) {
                    react(bunny,'s');
                }
                else if (isOffScreen(bunny)) {
                    bunny.move(0,-stepLen);
                }
            }
            else if (key == 'd') {
                bunny.move(stepLen,0);
                hop.setVolume(0.5);
                hop.play();
                if (isBlocked(bunny,false) || inWater(bunny)) {
                    react(bunny,'d');
                }
                else if (isOffScreen(bunny)) {
                    bunny.move(-stepLen,0);
                }
            }
            else if (key == 'a') {
                bunny.move(-stepLen,0);
                hop.setVolume(0.5);
                hop.play();
                if (isBlocked(bunny,false) || inWater(bunny)) {
                    react(bunny,'a');
                }
                else if (isOffScreen(bunny)) {
                    bunny.move(stepLen,0);
                }
            }
            else {}
        }
        else {
            remove(grass);
            remove(bunny);
            remove(pond);
            remove(water);
            remove(bear);
            remove(tiger);
            remove(tiger2);
            remove(score);
            remove(scoreboard);
            fireworks.setLocation(0,0);
            fireworks.setSize(getWidth(),getHeight());
            add(fireworks);
            congrats = new GLabel ("You Won!! Nice Job!!",getWidth()/4,getHeight()/2);
            congrats.setFont(new Font("Serif",Font.BOLD,50));
            congrats.setColor(Color.red);
            add(congrats);
            victory.setVolume(0.5);
            victory.play();
        }
    }

    public void mouseReleased (MouseEvent e) {
        if (!gameOver) {
            if (tiger.contains(e.getX(),e.getY()) || tiger2.contains(e.getX(),e.getY())) {
                growl.setVolume(0.5);
                growl.play();
            }
            else if (bear.contains(e.getX(),e.getY())) {
                roar.setVolume(0.5);
                roar.play();
            }
            else if (pond.contains(e.getX(),e.getY())) {
                splash.setVolume(0.5);
                splash.play();
            }
            else if (speedHolder.contains(e.getX(),e.getY())) {
                if (speedMode.equals("normal")) {
                    switchSpeed("fast");
                }
                else if (speedMode.equals("fast")) {
                    switchSpeed("slow");
                }
                else if (speedMode.equals("slow")) {
                    switchSpeed("normal");
                }
            }
        }
    }

    void setScore(int newTotal) {
        score.setLabel("Eggs To Collect: " + newTotal);
        if (newTotal == 0) {
            gameOver = true;
        }
    }

    void switchSpeed (String mode) {
        if (mode.equals("fast")) {
            speedSwitch.setLabel("Slow Speed");
            speedHolder.setFillColor(Color.red);
            stepLen = 40;
            speedMode = "fast";
        }
        else if (mode.equals("normal")) {
            speedSwitch.setLabel("Fast Speed!");
            speedHolder.setFillColor(Color.green);
            stepLen = 20;
            speedMode = "normal";
        }
        else if (mode.equals("slow")) {
            speedSwitch.setLabel("Normal Speed");
            speedHolder.setFillColor(Color.yellow);
            stepLen = 10;
            speedMode = "slow";
        }
    }

    void react (GObject subject, char key) {
        double scrWidth = getWidth();

        if (inWater(subject)) {
            remove(subject);
            splash.setVolume(0.5);
            splash.play();
            waitMSecs(1000);
            subject.setLocation(scrWidth-150,10);
            add(subject);
        }
        else {
            GObject obstacle = touchingWhat(bunny);
            if (obstacle == bear) {   
                remove(subject);
                roar.setVolume(0.5);
                roar.play();
                int dur = (int)Math.ceil(roar.getDuration());
                waitMSecs(dur*1000);
                subject.setLocation(scrWidth-150,10);
                add(subject);
            }
            else if (obstacle == tiger) {
                remove(subject);
                growl.setVolume(0.5);
                growl.play();
                int dur = (int)Math.ceil(growl.getDuration());
                waitMSecs(dur*1000);
                subject.setLocation(scrWidth-150,10);
                add(subject);
            }
            else if (obstacle == tiger2) {
                remove(subject);
                growl.setVolume(0.5);
                growl.play();
                int dur = (int)Math.ceil(growl.getDuration());
                waitMSecs(dur*1000);
                subject.setLocation(scrWidth-150,10);
                add(subject);
            }
            else if (obstacle == scoreboard) {
                //only two ways to approach scoreboard: heading left or heading up
                if (key == 'w') {
                    subject.move(0,stepLen);
                }
                else if (key == 'a') {
                    subject.move(stepLen,0);
                }
            }
            else if (obstacle == speedSwitch) {
                if (key == 'w') {
                    subject.move(0,stepLen);
                }
                else if (key == 'a') {
                    subject.move(stepLen,0);
                }
                else if (key == 'd') {
                    subject.move(-stepLen,0);
                }
            }
            else { //must be an egg
                waitMSecs(50);
                remove(obstacle);
                crack.setVolume(0.5);
                crack.play();
                obstacle.setLocation(scrWidth/2,-100); //dump found eggs off screen, to prevent further interaction with bunny
                waitMSecs(50);
                total--;
                setScore(total);
            }
        }
    }

    boolean isOffScreen (GObject subject) {
        double scrWidth = getWidth();
        double scrHeight = getHeight();
        double x = subject.getX();
        double y = subject.getY();
        if (x <= 0 || x >= (scrWidth - subject.getWidth()) || y <= 0 || y >= (scrHeight-subject.getHeight())) {
            return true;
        }
        return false;
    }

    GObject touchingWhichEgg (GObject subject) {
        GImage touchingEgg = null;
        for (int i = 0; i < eggs.length; i++) {
            if (areTouching(subject,eggs[i])) {
                touchingEgg = eggs[i];
            }
        }
        return touchingEgg;
    }

    GPoint randomLocation (double minX, double maxX, double minY, double maxY) {
        double x = rand.nextDouble(minX,maxX);
        double y = rand.nextDouble(minY,maxY);
        GPoint coord = new GPoint(x,y);
        return coord;
    }

    void assignSpot (GObject subject, boolean checkCramped, double minX, double maxX, double minY, double maxY) {
        subject.setLocation(randomLocation(minX,maxX,minY,maxY));
        int tolerance = 40;
        if (checkCramped) {
            if (isBlocked(subject,true) || isCramped(subject,tolerance)) {
                reassignToAvoid(subject,minX,maxX,minY,maxY);
            }
        }
        else {
            if (isBlocked(subject,true)) {
                reassignToAvoid(subject,minX,maxX,minY,maxY);
            }
        }
    }

    void reassignToAvoid (GObject subject, double minX, double maxX, double minY, double maxY) {
        int tries = 0;
        double tolerance = 40;
        GImage scaledSub = null;
        while (isBlocked(subject,true) || isCramped(subject,tolerance)) {
            if (tries >= 20000) {
                break;
            }
            else {
                if (tries % 500 == 0 && tries != 0) {
                    tolerance -= 0.5;
                }
                subject.setLocation(randomLocation(minX, maxX, minY, maxY));
                tries++;
            }
        }
    }

    GObject touchingWhat (GObject subject) {
        GObject object = null;
        if (areTouching(subject,bear)) {
            object = bear;
        }
        else if (areTouching(subject,tiger)) {
            object = tiger;
        }
        else if (areTouching(subject,tiger2)) {
            object = tiger2;
        }
        else if (touchingWhichEgg(subject) != null) {
            object = touchingWhichEgg(subject);
        }
        else if (areTouching(subject,scoreboard)) {
            object = scoreboard;
        }
        return object;
    }

    boolean isBlocked (GObject subject, boolean countingPond) {
        if (countingPond) {
            if (areTouching(subject,pond) && subject != pond) {
                return true; //checks if on top of any game elements (not including eggs)
            }
        }
        else {
            if (areTouching(subject,tiger) && subject != tiger) {
                return true;
            }
            else if (areTouching(subject,bear) && subject != bear) {
                return true;
            }
            else if (areTouching(subject,tiger2) && subject != tiger2) {
                return true;
            }
            else if (areTouching(subject,scoreboard) && subject != scoreboard) {
                return true;
            }
            else if (touchingWhichEgg(subject) != null && touchingWhichEgg(subject) != subject) {
                return true; //checks if on top of egg
            }
        }
        return false;
    }

    boolean isCramped (GObject subject, double tolerance) {
        if (areNear(subject,pond,tolerance) && subject != pond) {
            return true; //checks if on top of any game elements (not including eggs)
        }
        else if (areNear(subject,tiger,tolerance) && subject != tiger) {
            return true;
        }
        else if (areNear(subject,bear,tolerance) && subject != bear) {
            return true;
        }
        for (int i = 0; i < eggs.length; i++) {
            if (areNear(subject,eggs[i],tolerance) && eggs[i] != subject) {
                return true;
            }
        }
        return false;
    }

    boolean areTouching (GObject a, GObject b) {
        if (b != null) {
            GRectangle aBounds = a.getBounds();
            GRectangle bBounds = b.getBounds();
            if (aBounds.intersects(bBounds)) {
                return true;
            }
        }
        return false;
    }

    boolean insideAnything (GObject a) {
        GPoint spot = new GPoint (a.getX(),a.getY());
        if (bear.contains(spot) || tiger.contains(spot) || tiger2.contains(spot) || pond.contains(spot)) {
            return true;
        }
        return false;
    }

    boolean inWater (GObject subject) {
        GPoint loc = new GPoint (subject.getX(),subject.getY());
        if (water.contains(loc)) {
            return true;
        }
        return false;
    }

    boolean areNear (GObject a, GObject b, double tolerance) {
        if (b != null) {
            GRectangle aBounds = a.getBounds();
            GRectangle bBounds = b.getBounds();
            double expandedX = aBounds.getWidth() + tolerance;
            double expandedY = bBounds.getHeight() + tolerance;
            aBounds.setSize(expandedX,expandedY);
            if (aBounds.intersects(bBounds)) {
                return true;
            }
        }
        return false;
    }

    boolean isEgg (GObject a) {
        for (int i = 0; i < eggs.length; i++) {
            if (a == eggs[i]) {
                return true;
            }
        }
        return false;
    }

    void waitMSecs (int mSecs) {
        try {
            Thread.sleep(mSecs);
        }
        catch (InterruptedException ex) {

        }
    }
}
