public class InterruptingThreads implements Runnable {

    public void run() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("(" + Thread.currentThread().getName() + ") Interrupted by exception!");
        }
        while(!Thread.interrupted()) {
            // do nothing here
        }
        System.out.println("(" + Thread.currentThread().getName() + ") Interrupted for the second time. ");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new InterruptingThreads(), "myThread");
        myThread.start();

        System.out.println("(" + Thread.currentThread().getName() + ") Sleeping in main thread for 5s....");
        Thread.sleep(5000);

        System.out.println("(" + Thread.currentThread().getName() + ") Interrupting my thread.");
        myThread.interrupt();

        System.out.println("(" + Thread.currentThread().getName() + ") Sleeping in main thread for 5s....");
        Thread.sleep(5000);

        System.out.println("(" + Thread.currentThread().getName() + ") Interrupting my thread.");
        myThread.interrupt();
    }
}
