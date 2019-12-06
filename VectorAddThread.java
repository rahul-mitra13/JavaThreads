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
        System.out.println("In here");
        for (int i = lo; i < hi; i++) {
            c[i] = a[i] + b[i];
        }
    }

    public static void add(int[] a, int[] b, int[] c) throws InterruptedException {
        int len = a.length;

        // Create and start 10 threads.
        VectorAddThread[] mythread = new VectorAddThread[10];
        for (int i = 0; i < 10; i++) {
            mythread[i] = new VectorAddThread(a, b, c, (i*len)/10, ((i+1)*len/10));
            mythread[i].start();
        }

        // Wait for the threads to finish
        for (int i = 0; i < 10; i++) {
            mythread[i].join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] A = new int[100];
        int[] B = new int[100];
        int[] C = new int[100];

        for (int i = 0; i < 100; i++) {
            A[i] = i;
            B[i] = i*5;
        }

        add(A, B, C);

        System.out.print("A: ");
        for (int i=0; i<100; i++)
           System.out.print(A[i] + " ");
        System.out.println();

        System.out.print("B: ");
        for (int i=0; i<100; i++)
           System.out.print(B[i] + " ");
        System.out.println();

        System.out.print("C: ");
        for (int i=0; i<100; i++)
           System.out.print(C[i] + " ");
        System.out.println();
    }
}
