package coffee.order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderService board = new CoffeeOrderBoard();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        board.add("Анна");
        board.add("Максим");
        board.add("Ирина");

        System.out.println("CoffeeOrderBoard ");
        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить заказ");
            System.out.println("2 - Выдать ближайший заказ");
            System.out.println("3 - Выдать заказ по номеру");
            System.out.println("4 - Показать очередь");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Введите имя клиента: ");
                    String name = scanner.nextLine();
                    board.add(name);
                    break;

                case "2":
                    board.deliver();
                    break;

                case "3":
                    System.out.print("Введите номер заказа для выдачи: ");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());
                        board.deliver(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат номера. Введите целое число.");
                    }
                    break;

                case "4":
                    board.draw();
                    break;

                case "0":
                    running = false;
                    System.out.println("До свидания!");
                    break;

                default:
                    System.out.println("Неверная команда. Повторите ввод.");
            }
        }

        scanner.close();
    }

}