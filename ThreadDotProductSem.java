/*
*Rahul Mitra
*CPSC 316, Assignment 7, Part 3 - ThreadDotProductSem
*/

/*
* After compilation, please run with the flag java -Xmx30G ThreadDotProductSem n t for very large n
*Test Results
* size =  134217728 t = 1, time = 93 milliseconds
* size =  134217728 t = 2, time = 82 milliseconds
* size =  134217728 t = 4, time = 77 milliseconds
* size =  134217728 t = 8, time = 81 milliseconds
* throws an error if I try to create 32 threads
*/
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
   public class ThreadDotProductSem extends Thread{
   private int lo, hi, i;
   private int[] a, b;
   private Semaphore sem;
   private long localsum = 0;
   static AtomicLong sum = new AtomicLong(0);
   public ThreadDotProductSem(int[] a, int[] b, int lo, int hi, Semaphore sem, int i){
        this.a = a;
        this.b = b;
        this.lo = lo;
        this.hi = hi;
        this.sem = sem;
        this.i = i;
    }
    public void run() {
      localsum = 0;
      for (int i = lo; i < hi; i++) {
            localsum += a[i] * b[i];
        }
      try{
          sem.acquire();
          sum.addAndGet(localsum);
       }
      catch(InterruptedException exc){
         System.out.println(exc);
      }
       sem.release();
   }
   public static void dot(int[] a, int[] b,int numthreads, Semaphore sem) throws InterruptedException {
        int len = a.length;
         
        ThreadDotProductSem[] mythread = new ThreadDotProductSem[numthreads];
        for (int i = 0; i < numthreads; i++) {
            mythread[i] = new ThreadDotProductSem(a, b,(i*len)/numthreads, ((i+1)*len)/numthreads, sem, i);
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
        Semaphore sem = new Semaphore(1);//binary semaphore
        int[] A = new int[size];
        int[] B = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt(10);
            B[i] = rand.nextInt(10);
        }
        long start = System.nanoTime();
        dot(A, B, numthreads, sem);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
        System.out.println("The dot product is = "+sum);
    }
}
