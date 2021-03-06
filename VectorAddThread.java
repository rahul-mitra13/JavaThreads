/*
 * This program adds two vectors using 10 threads: C = A + B
 */
public class VectorAddThread extends Thread {
    private int lo, hi;
    private int[] a, b, c;
    
    public VectorAddThread(int[] a, int[] b, int[] c, int lo, int hi) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.lo = lo;
        this.hi = hi;
    }
    
    public void run() {
        for (int i = lo; i < hi; i++) {
            c[i] = a[i] + b[i];
        }
    }
    
    public static void add(int[] a, int[] b, int[] c, int numthreads) throws InterruptedException {
        int len = a.length;
        
        // Create and start 10 threads.
        VectorAddThread[] mythread = new VectorAddThread[numthreads];
        for (int i = 0; i < numthreads; i++) {
            mythread[i] = new VectorAddThread(a, b, c, (i*len)/10, ((i+1)*len/10));
            mythread[i].start();
        }
        
        // Wait for the threads to finish
        for (int i = 0; i < numthreads; i++) {
            mythread[i].join();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        int numthreads = Integer.valueOf(args[1]);
        int size = Integer.valueOf(args[0]);
        int[] A = new int[size];
        int[] B = new int[size];
        int[] C = new int[size];

        for (int i = 0; i < 100; i++) {
            A[i] = i;
            B[i] = i*5;
        }
        long start = System.nanoTime();
        add(A, B, C, numthreads);
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000; // in milliseconds
        System.out.println("Execution time is " + elapsed + " milliseconds");
       
        /*       
        System.out.print("A: ");
        for (int i=0; i<size; i++)
           System.out.print(A[i] + " ");
        System.out.println();

        System.out.print("B: ");
        for (int i=0; i<size; i++)
           System.out.print(B[i] + " ");
        System.out.println();

        System.out.print("C: ");
        for (int i=0; i<size; i++)
           System.out.print(C[i] + " ");
        System.out.println();
        */
    }
}
