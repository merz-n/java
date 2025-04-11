import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many CATS will there be: ");
        int countCats = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < countCats; i++){
            System.out.print("Input cat name  #" + (i + 1) + ": ");
            String name = scanner.nextLine();
            Cat cat = new Cat(name);

            System.out.print("Input distans run: ");
            int runDistance = scanner.nextInt();
            scanner.nextLine();
            cat.run(runDistance);

            System.out.print("Distans swim for cats = 0 ");
            /*int swimDistance = scanner.nextInt();
            cat.swim(swimDistance);
            scanner.nextLine(); // считываем \n*/
            int swimDistance = 0;
            cat.swim(swimDistance);
            System.out.println();
        }

        System.out.println("How many Dogs will there be: ");
        int countDogs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i<countDogs; i++){

            System.out.print("Input dog name  #" + (i + 1) + ": ");
            String name = scanner.nextLine();
            Dog dog = new Dog(name);

            System.out.print("Input distans run: ");
            int runDistance = scanner.nextInt();
            scanner.nextLine();
            dog.run(runDistance);

            System.out.print("Input distans swim: ");
            int swimDistance = scanner.nextInt();
            scanner.nextLine();
            dog.swim(swimDistance);
            scanner.nextLine();
            System.out.println();
        }
        System.out.println("Cats: " + Cat.count);
        System.out.println("Dogs: " + Dog.count);
        System.out.println("All animals: " + Animal.count);

        scanner.close();

    }
}