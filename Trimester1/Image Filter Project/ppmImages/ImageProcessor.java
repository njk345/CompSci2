/* This project allows the user to enter a ppm image file and have one of four transformations be performed on it. For three of the 
 * options, a collage can also be made between the original and the transformed version.
 * 
 * @author Nick Keirstead
 * @version 11/25/14
 */
import java.util.Scanner;
import java.util.Random; //unlike Math.random, can given integer rand nums
import java.io.PrintWriter;


//it is possible to transform one file multiple times by having the user enter in the output file as the input file on a subsequent
//time through the main while loop --> but it will just add an -out to the name each time --> blocksout.ppm --> blocksoutout.ppm -->etc.
public class ImageProcessor
{
    public void runEditor() throws java.io.IOException, InterruptedException {
        boolean again = true;
        boolean doneBefore = false;
        while (again) {
            clear();
            if (!doneBefore) {
                displayWelcome();  
            }
            else {
                greetAgain();
            }

            Scanner input = new Scanner (System.in);
            String rawName = getFilename (input);
            
            stop();
            blankLine();
            System.out.println("Ok I will now edit " + rawName + "!");
            System.out.println();

            Scanner inPic = openInputFile(rawName);
            Scanner inPic2 = openInputFile(rawName); //copy scanner on input file to use later with collage b/c can't reset scanner once used.
            PrintWriter outPic = openOutputFile(rawName);

            
            stop();
            chooseTransform(input,inPic,inPic2,outPic);
            stop();
            confirmTransform();
            
            stop();
            System.out.println("Would you like to edit another file? (y/n)");
            inpSymbol();
            String ans = input.next();
            if (ans.equals("n") || ans.equals("N")) {
                break;
            }
            doneBefore = true;
        }
        stop();
        displayGoodbye();
    }

    //**** USER INTERFACE METHODS
    public void displayWelcome () {
        System.out.println("***************");
        System.out.println("Welcome to the image editor!");
        System.out.println("Created by Nick Keirstead");
        System.out.println("***************");
        blankLine();
    }

    public void confirmTransform () {
        blankLine();
        System.out.println("Ok, your new file is ready — Check your folder!");
        blankLine();
    }

    public void greetAgain () {
        System.out.println("***************");
        System.out.println("Welcome Back!");
        System.out.println("You may now edit another file:");
    }

    public void displayGoodbye () {
        blankLine();
        System.out.println("***************");
        System.out.println("Goodbye...from your friends at the image editor!");
    }

    public void blankLine() {
        System.out.println();
    }
    
    public void inpSymbol () {
        System.out.print(">>>  ");
    }

    public void clear () {
        System.out.println("\f");
    }
    
    public void stop () throws InterruptedException {
        Thread.sleep(800);
    }

    //**** GETTER AND SETTER METHODS
    public String getFilename (Scanner s) {
        System.out.println("Please enter a file to be processed.");
        inpSymbol();
        String name = s.next();
        if (name.endsWith(".ppm")) {
            return name;
        }
        else {
            name += ".ppm";
            return name;
        }
    }

    public void chooseTransform (Scanner s, Scanner inputPic, Scanner inputPic2, PrintWriter outputPic) throws InterruptedException {
        System.out.println("Choose your filter!");
        System.out.println("    1: Flatten_Blue");
        System.out.println("    2: Extreme_Contrast");
        System.out.println("    3: Improved_Scale");
        System.out.println("    4: Random_Noise");
        System.out.println("    5: Blend_Middle");
        System.out.println();
        inpSymbol();
        int choice = s.nextInt();

        if (choice != 3) {//as long as is not scaleImproved, collage can be performed
            stop();
            System.out.println();
            System.out.println("Would you like a collage out of that? (type true or false)");
            inpSymbol();
            boolean collage = s.nextBoolean();
            enactFilter(s,choice,collage,inputPic,inputPic2,outputPic);
        }
        else {
            enactFilter(s,choice,false,inputPic,inputPic2,outputPic); //if scaleImproved chosen, just go without collage option
        }
    }

    public void enactFilter (Scanner userStream, int choice, boolean isCollage, Scanner inputPic, Scanner inputPic2, PrintWriter outputPic) throws InterruptedException {
        switch (choice) {
            case 1:
                flattenBlue(inputPic,inputPic2,outputPic,isCollage);
                break;
            case 2:
                extremeContrast(inputPic,inputPic2,outputPic,isCollage);
                break;
            case 3:
                stop();
                System.out.println();
                System.out.println("Enter your integer scale factor (>=2):");
                inpSymbol();
                int k = userStream.nextInt();
                improvedScale(inputPic,outputPic,k);
                break;
            case 4:
                stop();
                System.out.println();
                System.out.println("Enter max random number value:");
                inpSymbol();
                int m = userStream.nextInt();
                randomNoise(inputPic,inputPic2,outputPic,m,isCollage);
                break;
            case 5:
                blendMiddle(inputPic,inputPic2,outputPic,isCollage);
        }
    }

    //**** HELPER METHODS
    public Scanner openInputFile (String filename) throws java.io.IOException {
        String fullFileName = System.getProperty("user.dir") + "/" + filename;
        java.io.File picfile = new java.io.File (fullFileName); //define as java file --> can be read and written to
        Scanner picScan = new Scanner (picfile); //here's the final file scanner mechanism
        return picScan;
    }

    public PrintWriter openOutputFile (String filename) throws java.io.IOException {
        filename = filename.substring(0,filename.length()-4); //chop previous ".ppm"
        String fullFileName = System.getProperty("user.dir") + "/" + filename + "out.ppm";

        System.out.println("Transformed file will be: " + fullFileName);
        blankLine();

        java.io.File picfile = new java.io.File (fullFileName);
        PrintWriter picScan = new PrintWriter (picfile);
        return picScan;
    }

    public int howHigh(int pix, int pixLim) {
        if (pix <= Math.floor(pixLim/2)) {
            return 0;
        }
        else {
            return pixLim;
        }
    }

    public int randomFix (int pix, int pixLim, int randLim) {
        Random randGen = new Random();
        int sign = randGen.nextInt(2); //gives 0 or 1

        if (sign == 0) {//add the random num
            int rand = randGen.nextInt(randLim);
            int newPix = pix + rand;
            newPix = newPix > pixLim? pixLim: newPix; //if modded val > pixel limit, reduce to limit
            return newPix;
        }
        else {//subtract the randum num
            int rand = randGen.nextInt(randLim);//can only get to randLim -1
            int newPix = pix - rand;
            newPix = newPix < 0? 0: newPix; //if modded val > pixel limit, reduce to limit
            return newPix;
        }
    }

    //**** TRANSFORMATION METHODS
    /*public void copyToScreen (Scanner picture) {//straight file spit out --> UNUSED IN PROGRAM
    String p3 = picture.next();
    int numCols = picture.nextInt(); 
    int numRows = picture.nextInt();
    int max = picture.nextInt();
    System.out.println(p3);
    System.out.println(numCols + " " + numRows);
    System.out.println(max);

    //now we go in and extract rgb triplets
    for (int row = 0; row < numRows; row++) {
    for (int col = 0; col < numCols; col++) {
    int r = picture.nextInt(); //temporary vars to spit the rgb triplets out
    int g = picture.nextInt();
    int b = picture.nextInt();
    System.out.print(r + " " + g + " " + b + " ");
    }
    System.out.println(); //line split between each row
    }
    }*/
    public void flattenBlue (Scanner inputPic, Scanner inputPic2, PrintWriter outputPic, boolean isCollage) {
        String p3 = inputPic.next();
        int numCols = inputPic.nextInt(); 
        int numRows = inputPic.nextInt();
        int max = inputPic.nextInt(); //highest rgb value allowed
        outputPic.println(p3);
        if (isCollage) {
            outputPic.println(numCols + " " + 2*numRows);
        }
        else {
            outputPic.println(numCols + " " + numRows);
        }
        outputPic.println(max);

        //now we go in and extract rgb triplets
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int r = inputPic.nextInt();
                int g = inputPic.nextInt();
                int b = inputPic.nextInt();
                outputPic.print(r + " " + g + " " + 0 + "    ");
            }
            outputPic.print("\n");
        } 
        if (isCollage) {
            appendOld(inputPic2,outputPic);
        }
        outputPic.flush();
    } 

    public void extremeContrast (Scanner inputPic, Scanner inputPic2, PrintWriter outputPic, boolean isCollage) {
        String p3 = inputPic.next();
        int numCols = inputPic.nextInt(); 
        int numRows = inputPic.nextInt();
        int max = inputPic.nextInt(); //highest rgb value allowed
        outputPic.println(p3);
        if (isCollage) {
            outputPic.println(numCols + " " + 2*numRows);
        }
        else {
            outputPic.println(numCols + " " + numRows);
        }
        outputPic.println(max);

        //if rgb pixel is less than half the max, make 0. If more than half, make max.
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++ ) {
                int r = howHigh(inputPic.nextInt(),max);
                int g = howHigh(inputPic.nextInt(),max);
                int b = howHigh(inputPic.nextInt(),max);
                outputPic.print(r + " " + g + " " + b + "    ");
            }
            outputPic.print("\n");
        }
        if (isCollage) {
            appendOld(inputPic2,outputPic);
        }
        outputPic.flush();
    }

    public void randomNoise (Scanner inputPic, Scanner inputPic2, PrintWriter outputPic, int randLim, boolean isCollage) {
        String p3 = inputPic.next();
        int numCols = inputPic.nextInt(); 
        int numRows = inputPic.nextInt();
        int max = inputPic.nextInt(); //highest rgb value allowed
        outputPic.println(p3);
        if (isCollage) {
            outputPic.println(numCols + " " + 2*numRows);
        }
        else {
            outputPic.println(numCols + " " + numRows);
        }
        outputPic.println(max);

        if (randLim > max) {
            randLim = max; //if inputted limit is beyond pixel limit of file, reduce to the limit
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int r = randomFix(inputPic.nextInt(),max,randLim);
                int g = randomFix(inputPic.nextInt(),max,randLim);
                int b = randomFix(inputPic.nextInt(),max,randLim);
                outputPic.print(r + " " + g + " " + b + "    ");
            }
            outputPic.print("\n");
        }
        if (isCollage) {
            appendOld(inputPic2,outputPic);
        }
        outputPic.flush();
    }

    public void improvedScale (Scanner inputPic, PrintWriter outputPic, int factor) {
        //factor is assumed to be >= 2 and an integer
        String p3 = inputPic.next();
        int numCols = inputPic.nextInt();
        int numRows = inputPic.nextInt();
        int max = inputPic.nextInt();

        int newCols = numCols * factor;
        int newRows = numRows * factor;

        outputPic.println(p3);
        outputPic.println(newCols + " " + newRows);
        outputPic.println(max);

        String tempLine;
        for (int row = 0; row < numRows; row++) {
            tempLine = "";
            for (int col = 0; col < numCols; col++) {
                int r = inputPic.nextInt();
                int g = inputPic.nextInt();
                int b = inputPic.nextInt();

                for (int i = 0; i < factor; i++) {
                    outputPic.print(r + " " + g + " " + b + "    ");
                    tempLine += r + " " + g + " " + b + "    ";
                }
            }
            outputPic.print("\n");
            for (int i = 1; i < factor; i++) {
                outputPic.println(tempLine);//repeats the line however many times --> scaling picture in vertical direction as well
            }
        } 
        outputPic.flush();
    }

    public void blendMiddle (Scanner inputPic, Scanner inputPic2, PrintWriter outputPic, boolean isCollage) {
        String p3 = inputPic.next();
        int numCols = inputPic.nextInt();
        int numRows = inputPic.nextInt();
        int max = inputPic.nextInt();

        outputPic.println(p3);
        if (isCollage) {
            outputPic.println(numCols + " " + 2*numRows);
        }
        else {
            outputPic.println(numCols + " " + numRows);
        }
        outputPic.println(max);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int r = inputPic.nextInt();
                int g = inputPic.nextInt();//g val thrown away
                int b = inputPic.nextInt();

                g = (r + b)/2;
                outputPic.print(r + " " + g + " " + b + "    ");
            }
            outputPic.print("\n");
        }
        if (isCollage) {
            appendOld(inputPic2,outputPic);
        }
        outputPic.flush();
    }

    public void appendOld (Scanner inPic, PrintWriter outputPic) {
        //throw away the preamble; read from duplicate inputFile scanner that has been passed along
        String p3 = inPic.next();
        int numCols = inPic.nextInt(); 
        int numRows = inPic.nextInt();
        int max = inPic.nextInt();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int r = inPic.nextInt();
                int g = inPic.nextInt();
                int b = inPic.nextInt();
                outputPic.print(r + " " + g + " " + b + "    ");
            }
            outputPic.print("\n");
        }
    }
}
