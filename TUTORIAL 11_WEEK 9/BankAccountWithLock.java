import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

public class BankAccountWithLock {
    private double balance;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public BankAccountWithLock(double initialBalance) {
        this.balance = initialBalance;
    }

    //Read balance (shared lock)
    public double getBalance() {
        readLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + " reads balance: " + balance);
            return balance;
        } finally {
            readLock.unlock();
        }
    }

    //Deposit money (exclusive lock)
    public void deposit(double amount) {
        writeLock.lock();

        try{
            System.out.println(Thread.currentThread().getName() + " deposits: " + amount);

            balance += amount;
        } finally {
            writeLock.unlock();
        }
    }

    //Withdraw money (exclusive lock)
    public void withdraw(double amount) {
        writeLock.lock();

        try{
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " withdraws: " + amount);

                balance -= amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for: " + amount);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        BankAccountWithLock account = new BankAccountWithLock(100.0);

        //Deposit money
        Thread deposit = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.deposit(100.0);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Depositor-");

        Thread withdraw = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(500.0);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Withdrawal-");

        deposit.start();
        withdraw.start();

        try {
            deposit.join();
            withdraw.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.getBalance();
    }
}
