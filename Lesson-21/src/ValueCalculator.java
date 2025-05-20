public class ValueCalculator {
    public void doCalc() {
        long start = System.currentTimeMillis();
        float[] arr = new float[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        int half = arr.length/2;

        float[] part1 = new float[half];
        float[] part2 = new float[half];

        System.arraycopy(arr, 0, part1, 0,half);
        System.arraycopy(arr, half, part2, 0,half);

        System.out.println("part1 arr: " + part1.length);
        System.out.println("part2 arr: " + part2.length);

        Runnable task1 = new CalcTask(part1,0);
        Runnable task2 = new CalcTask(part2,half);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(part1,0,arr,0,half);
        System.arraycopy(part2,0,arr,half,half);
        long end = System.currentTimeMillis();
        System.out.println("Task execution time: " + (end-start) + " mc" );
    }
}
