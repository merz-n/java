package iprody.coffeeshot;

public class Order {
    private String name;
    private int orderNumber;

    public Order(String name, int orderNumber) {
        this.name = name;
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Name='" + name + '\'' +
                ", OrderNumber=" + orderNumber +
                '}';
    }
}
