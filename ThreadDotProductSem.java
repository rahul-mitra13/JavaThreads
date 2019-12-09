import java.util.Random;
public class ThreadDotProductSem extends Thread{
   private static int lo, hi, sum;
   private static int[] a, b;
   public ThreadDotProductSem(int[] a, int[] b, int lo, int hi, int sum){
        this.a = a;
        this.b = b;
        this.lo = lo;
        this.hi = hi;
        this.sum = sum;
    }
    public void run() {
        for (int i = lo; i < hi; i++) {
            sum += a[i] * b[i];
        }
    }

    public static synchronized void dot(int[] a, int[] b,int numthreads, int sum) throws InterruptedException {
        int len = a.length;
         
        // Create and start 10 threads.
        ThreadDotProductSem[] mythread = new ThreadDotProductSem[numthreads];
        for (int i = 0; i < numthreads; i++) {
            mythread[i] = new ThreadDotProductSem(a, b,(i*len)/numthreads, ((i+1)*len/numthreads), 0);
            mythread[i].start();
        }

        // Wait for the threads to finish
        for (int i = 0; i < numthreads; i++) {
            mythread[i].join();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        /*int size = Integer.valueOf(args[0]);
        int numthreads = Integer.valueOf(args[1]);
        Random rand = new Random();
        int[] A = new int[size];
        int[] B = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt();
            B[i] = rand.nextInt();
        }*/
        int A[] = {1,1,1,1,1,1,1,1,1,1};
        int B[] = {1,1,1,1,1,1,1,1,1,1};
        Semaphore sem = new Semaphore(5);
        long start = System.nanoTime();
        dot(A, B, 5, sum);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
        System.out.println("The dot product is = "+sum);
    }
}
