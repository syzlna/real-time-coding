class MyThread extends Thread {
    int number;

    public MyThread(int number) {
        this.number = number;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Thread- " + number + ": " + number + " x " + i + " = " + (number * i));
            try {
                Thread.sleep(500); // pause to make output readable
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class MyVolatile {
    public static void main(String[] args) {
        MyThread t1 = new MyThread(0);
        MyThread t2 = new MyThread(1);
        MyThread t3 = new MyThread(2);

        t1.start();
        t2.start();
        t3.start();
    }
}
