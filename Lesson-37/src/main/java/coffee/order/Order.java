package coffee.order;

public class Order {
    private int orderNummer;
    private String customerName;

    public Order(int orderNummer, String customerName) {
        this.orderNummer = orderNummer;
        this.customerName = customerName;
    }

    public int getOrderNummer() {
        return orderNummer;
    }

    public void setOrderNummer(int orderNummer) {
        this.orderNummer = orderNummer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNummer=" + orderNummer +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
