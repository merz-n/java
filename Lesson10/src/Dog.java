public class Dog extends Animal {
    public static int count = 0;

    public Dog(String name) {
        super(name, 10, 500);
        count++;
    }
    @Override
    public void run(int distance) {
        if (distance <= limitRun){
            System.out.println(" Dog: " + name + " run " + distance+ " m ");
        } else {
            System.out.println(" Dog: " + name + " don't run like that ");
        }

    }

    @Override
    public void swim(int distance) {
        if (distance <= limitSwim){
            System.out.println("Dog: " + name + " swim " + distance+ " m ");
        } else {
            System.out.println("Dog: " + name + " don't swim like that ");
        }
    }

    @Override
    public void display() {
        System.out.println(" Dogs : " + Dog.count);

    }
}
