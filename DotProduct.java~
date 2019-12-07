/*
*Test Results
* size = 100, time = 0 milliseconds
* size = 1000, time = 0 milliseconds
* size = 10000, time = 0 milliseconds
* size = 100000, time = 0 milliseconds
* size = 1000000, time = 4 milliseconds
* size = 10000000, time = 9 milliseconds
* size = 100000000, time = 45 milliseconds
* size = 1000000000, time = Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at DotProduct.main(DotProduct.java:7)
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
