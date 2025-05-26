import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PetrolStation {
    private double fuelAmount;
    private final Lock lock = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(3);

    public PetrolStation(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void doTank(double amount) throws InterruptedException{
        semaphore.acquire();
        try {
            lock.lock();
            try {
                if (fuelAmount >= amount) {
                    fuelAmount -= amount;
                    System.out.println("Petrol station has: " + fuelAmount + " fuel, car refueled amout " + amount);
                } else {
                    throw new RuntimeException("Not enought fuel");
                }

            } finally {
                lock.unlock();
            }
            int delay = 3000 + new Random().nextInt(7000);
            Thread.sleep(delay);
        } finally {
            semaphore.release();
        }
    }
}
