package iprody.coffeeshot;
import java.util.Arrays;
import java.util.*;

public class CoffeeOrderBoard {
    private List<Order> orders = new LinkedList<>();
    private int orderNumber = 1;

    public void add(String name) {
        Order newOrder = new Order(name, orderNumber);
        orders.add(newOrder);
        orderNumber++;
    }

    public void deliver() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();
            System.out.println("Order nummer: " + order.getOrderNumber() + ", for: " + order.getName());

        } else {
            System.out.println("No Orders");
        }
    }

    public void deliverPlus(int number) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderNumber() == number) {
                iterator.remove();
                System.out.println("Order nummer: " + order.getOrderNumber() + " - name: " + order.getName());
                return;
            }
        }

    }
    public void draw(){
        System.out.println("=============");
        System.out.println("Num | Name");
        for (Order order : orders){
            System.out.println( order.getOrderNumber() + " - " + order.getName());
        }

    }
}
