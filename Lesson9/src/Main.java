import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        Random random = new Random();
        String[] names = {"Иван Иванов", "Анна Смирнова", "Пётр Кузнецов", "Мария Орлова", "Сергей Лебедев", "Елена Новикова", "Дмитрий Фёдоров"};
        String[] positions = {"Разработчик", "Бухгалтер", "Менеджер", "Тестировщик", "Аналитик"};
        String[] emails = {"ivan@example.com", "anna@example.com", "petr@example.com", "maria@example.com", "sergey@example.com", "elena@example.com", "dmitry@example.com"};
        String[] phones = {"+49 123 111", "+49 123 222", "+49 123 333", "+49 123 444", "+49 123 555", "+49 123 666"};

        for (int i = 0; i < employees.length; i++) {
            String fullName = names[random.nextInt(names.length)];
            String position = positions[random.nextInt(positions.length)];
            String email = emails[random.nextInt(emails.length)];
            String phone = phones[random.nextInt(phones.length)];
            int salary = 3000 + random.nextInt(3001);
            int age = 25 + random.nextInt(26);

            employees[i] = new Employee(fullName, position, email, phone, salary, age);
        }
        for (int i = 0; i < employees.length; i++) {
            System.out.println("Сотрудник #" + (i + 1));
            employees[i].displayInfo();
            System.out.println("-------------------------");
        }
        System.out.println("Сотрудники старше 40 лет:");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40) {
                System.out.println("Сотрудник #" + (i + 1));
                employees[i].displayInfo();
                System.out.println("-------------------------");
            }
        }

    }
}