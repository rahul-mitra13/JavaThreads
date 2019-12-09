import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
   public class ThreadDotProductSem extends Thread{
   private static int lo, hi, i;
   private int[] a, b;
   private Semaphore sem;
   private int localsum = 0;
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
      System.out.println("This is low and i is = "+lo+" "+i);
      System.out.println("This is hi and i is = "+hi+" "+i);
      for (int i = lo; i < hi; i++) {
            localsum += a[i] * b[i];
        }
      System.out.println("This is the local sum = "+localsum);
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
            System.out.println("This is i = " + i);
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
        int dot2 = 0;
        for ( int i = 0; i < size; i++){
           dot2 += A[i]*B[i];
        }
        System.out.println("Actual dot product = "+dot2);
        long start = System.nanoTime();
        dot(A, B, numthreads, sem);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
        System.out.println("The dot product is = "+sum);
    }
}
