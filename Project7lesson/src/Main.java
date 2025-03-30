import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("////////1//////// ");
        System.out.print("Input nameFull: ");
        String nameFull = scanner.nextLine();
        System.out.print("Input position: ");
        String position = scanner.nextLine();
        System.out.print("Input email: ");
        String email = scanner.nextLine();
        System.out.print("Input phone: ");
        String phone = scanner.nextLine();
        System.out.print("Input age: ");
        int age = scanner.nextInt();
        Employee employee1 = new Employee(nameFull,position,email,phone,age);
        System.out.println(employee1);
        System.out.println("////////2//////// ");
        package1.SameName obj1 = new package1.SameName();
        obj1.printMessage();
        package2.SameName obj2 = new package2.SameName();
        obj2.printMessage();

        scanner.close();
    }
}