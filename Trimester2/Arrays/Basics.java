
/**
 * Basic usage of arrays. A few functions to find properties of the arrays/sort stuff.
 * 
 * @author Nick Keirstead 
 * @version 2/12/15
 */
public class Basics
{
   public static void main (String[] args) {
       int[] test = createBigArray();
       System.out.println(findMedian(test));
   }
   
   public static int [] createBigArray () {
       int [] array = new int[1000];
       for (int i = 0; i < array.length; i++) {
           array[i] = (int)(Math.random()*100);
       }
       return array;
   }
   public static void unpack (int[]array) {
       for (int i = 0; i < array.length; i++) {
           System.out.println(array[i]);
       }
   }
   public static int findMax (int[] array) {
       int max = array[0];
       for (int i = 1; i < array.length; i++) {
           if (array[i] > max) {
              max = array[i];
           }
       }
       return max;
   }
   public static int findMean (int [] array) {
       int sum = 0;
       for (int i = 0; i < array.length; i++) {
           sum += array[i];
       }
       sum /= array.length;
       return sum;
   }
   public static int findMedian (int [] array) {
       if (array.length % 2 == 0) {
           return array[array.length/2 - 1] + array[array.length/2];
       }
       else {
           return array[array.length/2];
       }
   }
}

/* Given an array of length n:
 * Start at box 0: This is the biggest number so far
 * Go to the next box: If number here is bigger than biggest number so far, biggest so far now eauals number in box
 * Repeat until n-1th box has been compared to biggest number.
 * Tell me what the "biggest number so far" is, by the end.
 */
