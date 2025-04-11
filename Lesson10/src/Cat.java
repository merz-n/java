public class Cat extends Animal {
    public static int count = 0;

    public Cat(String name) {
        super(name, 0, 200);
        count++;
    }
    @Override
    public void run(int distance) {
        if (distance <= limitRun){
            System.out.println(name + " run " + distance+ " m ");
        } else {
            System.out.println(" Cats don't run like that ");
        }

    }

    @Override
    public void swim(int distance) {
        System.out.println(" Cats can't swim ");
    }

    @Override
    public void display() {
        System.out.println("Cats : " + Cat.count);
    }
}
