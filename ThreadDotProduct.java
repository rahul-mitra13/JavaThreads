/*
*Test Results
* size =  134217728 t = 1, time = 88 milliseconds
* size =  134217728 t = 2, time = 80 milliseconds
* size =  134217728 t = 4, time = 74 milliseconds
* size =  134217728 t = 8, time = 73 milliseconds
* size =  134217728 t = 16, time = 70 milliseconds
* throws an error if I try to create 32 threads
*/
import java.util.Random;
public class ThreadDotProduct extends Thread{
   private int lo, hi, pos;
   private int[] a, b, c;
   public ThreadDotProduct(int[] a, int[] b,int c[], int lo, int hi, int pos){
        this.a = a;
        this.b = b;
        this.c = c;
        this.lo = lo;
        this.hi = hi;
        this.pos = pos;
    }
    public void run() {
        int sum = 0;//stores the local sum in each section
        for (int i = lo; i < hi; i++) {
          sum += a[i] * b[i];
        }
        c[pos] = sum;
    }

    public static void dot(int[] a, int[] b,int c[], int numthreads) throws InterruptedException {
        int len = a.length;
         
        // Create and start 10 threads.
        ThreadDotProduct[] mythread = new ThreadDotProduct[numthreads];
        for (int i = 0; i < numthreads; i++) {
            mythread[i] = new ThreadDotProduct(a, b, c, (i*len)/numthreads, ((i+1)*len/numthreads), i);
            mythread[i].start();
        }

        // Wait for the threads to finish
        for (int i = 0; i < numthreads; i++) {
            mythread[i].join();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int size = Integer.valueOf(args[0]);
        int numthreads = Integer.valueOf(args[1]);
        Random rand = new Random();
        int[] A = new int[size];
        int[] B = new int[size];
        int[] C = new int[numthreads];//array of size t where t is the number of threads
        int dot = 0;
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt();
            B[i] = rand.nextInt();
        }
        long start = System.nanoTime();
        dot(A, B, C, numthreads);
        for (int i = 0; i < numthreads; i++){
          dot += C[i];//summing all the local sums to get dot product
        }
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
        System.out.println("The dot product is = "+dot);
    }
}
