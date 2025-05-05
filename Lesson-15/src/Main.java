import iprody.coffeeshot.CoffeeOrderBoard;

import java.util.Arrays;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();
        coffeeOrderBoard.add("Anna");
        coffeeOrderBoard.add("Petja");
        coffeeOrderBoard.add("Alina");
        coffeeOrderBoard.add("Anna");
        coffeeOrderBoard.add("Annanna");
        coffeeOrderBoard.add("Petr");
        coffeeOrderBoard.add("Asti");
        coffeeOrderBoard.add("Witya");

        coffeeOrderBoard.draw();
        System.out.println("=============");
        System.out.println("Order with priority deliver.");
        coffeeOrderBoard.deliverPlus(4);
        coffeeOrderBoard.draw();
        System.out.println("=============");
        System.out.println("Order deliver. ");
        coffeeOrderBoard.deliver();
        coffeeOrderBoard.draw();
    }
}