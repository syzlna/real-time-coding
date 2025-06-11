package Synchronization_Exercise8;

public class Comparison {

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;

        //Normal
        Thread[] normalThreads = new NormalThread[threadCount];

        long normalStartTime = System.nanoTime();
        long normalEndTime = System.nanoTime();
        double normalTime = (normalEndTime - normalStartTime) / 1_000_000_000.0;

        for (int i = 0; i < threadCount; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }

        for (Thread t: normalThreads) {
            t.join();
        }

        //Synchronized
        Thread[] syncThreads = new SynchronizedThread[threadCount];

        long syncStartTime = System.nanoTime();
        long syncEndTime = System.nanoTime();
        double syncTime = (syncEndTime - syncStartTime) / 1_000_000_000.0;

        for (int i = 0; i < threadCount; i++) {
            syncThreads[i] = new SynchronizedThread();
            syncThreads[i].start();
        }

        for (Thread t: syncThreads) {
            t.join();
        }

        System.out.println("Normal thread = " + normalTime + " seconds");
        System.out.println("Synchronized thread = " + syncTime + " seconds");
    }
}

class NormalThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }
}

class SynchronizedThread extends Thread {
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }
}
