import java.util.Random;
import java.util.concurrent.Semaphore;
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
        int sum = 0;
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
        int[] C = new int[numthreads];
        int dot = 0;
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt();
            B[i] = rand.nextInt();
        }
        long start = System.nanoTime();
        dot(A, B, C, 2);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
        for (int i = 0; i < 2; i++){
          dot += C[i];
        }
        System.out.println("The dot product is = "+dot);
    }
}
