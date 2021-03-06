/*
*Rahul Mitra
*CPSC 316, Assignment 7, Part 3 - DotProduc
*/
/*
* * After compilation, please run with the flag java -Xmx30G DotProduct n for very large n
*Test Results
* size = 100, time = 0 milliseconds
* size = 1000, time = 0 milliseconds
* size = 10000, time = 0 milliseconds
* size = 100000, time = 0 milliseconds
* size = 1000000, time = 4 milliseconds
* size = 10000000, time = 9 milliseconds
* size = 100000000, time = 45 milliseconds
* size = 134217728, time = 92 milliseconds
* size = 536870912, time = 395 milliseconds
*/
import java.util.Random;
public class DotProduct{
  public static void main(String args[]){
    Random rand = new Random();
    int size = Integer.valueOf(args[0]);
    int dot = 0;
    int []vec1 = new int[size];
    int []vec2 = new int[size];
    //populate the vectors
    for ( int i = 0; i < size; i++){
      vec1[i] = rand.nextInt();
      vec2[i] = rand.nextInt();
    }
    //find dot product
    long start = System.nanoTime();
    for ( int i = 0 ; i < size; i++){
      dot += vec1[i] * vec2[i];
    }
    long end = System.nanoTime();
    long elapsed = (end - start) / 1000000; // in milliseconds
    System.out.println("The dot product = " +  dot);
    System.out.println("Execution time is " + elapsed + " milliseconds");
  }
}
