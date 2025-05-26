import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("_____________________ Task1 _________________________");
        ThreadSafeList<Integer> list = new ThreadSafeList<>();
        Thread[] adders = new Thread[5];
        for(int i = 0; i < adders.length; i++){
            final int thredId = i;
            adders[i] = new Thread(()-> {
                for(int j = 0; j < 10; j++){
                    list.add(thredId*1000+j);

                }
            });
            adders[i].start();
        }
        for (Thread adder : adders) {
            adder.join();
        }

        Thread[] reads = new Thread[2];
        for(int i = 0; i < reads.length; i++){
            reads[i] = new Thread(()-> {
                for(int j = 0; j < 5; j++){
                    Random random = new Random();
                    int size = list.size();
                    if(size == 0) continue;
                    int index = random.nextInt(size);
                    try {
                        Integer value = list.get(index);
                        System.out.println("Read value at " + index + ": " + value);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Tried to read invalid index: " + index);
                    }
                }
            });
            reads[i].start();
        }
        for (Thread read : reads){
            read.join();
        }
        System.out.println("_____________________ Task2 _________________________");
        PetrolStation petrolStation = new PetrolStation(1000.0);
        Thread[] client = new Thread[10];
        for(int i =0; i < client.length;i++){
            client[i] = new Thread(()->{
                    double amount = 20 + new Random().nextInt(70);
                    try {
                        petrolStation.doTank(amount);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Client interrupted");
                    } catch (RuntimeException e) {
                        System.out.println("Client feller " + e.getMessage());
                    }
            });
            client[i].start();
        }
        for (Thread clien :client){
            clien.join();
        }
    }

}
