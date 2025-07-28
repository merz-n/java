package coffee.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.LinkedList;

public class CoffeeOrderBoard implements CoffeeOrderService{
    Deque<Order> orders = new LinkedList<>();
    int lastOrder;
    private static final Logger logger = LogManager.getLogger(CoffeeOrderBoard.class);

    @Override
    public void add(String name) {
        lastOrder++;
        Order order= new Order(lastOrder,name);
        orders.addLast(order);
        logger.info("Добавлен заказ: номер = {}, имя клиента = {}", lastOrder, name);
    }

    @Override
    public void deliver() {
        if (orders.isEmpty()){
            logger.warn("Очередь пуста");
            return;
        }
        Order order = orders.removeFirst();
        logger.info("Выдан ближайший заказ: номер = {}, имя клиента = {}", order.getOrderNummer(), order.getCustomerName());
    }

    @Override
    public void deliver(int orderNumber) {
        try {
            Order foundOrder = null;
            for(Order order : orders){
                if(order.getOrderNummer() == orderNumber){
                    foundOrder = order;
                    break;
                }
            }
            if(foundOrder != null){
                orders.remove(foundOrder);
                logger.info("Выдан заказ по номеру: номер = {}, имя клиента = {}",
                        foundOrder.getOrderNummer(), foundOrder.getCustomerName());
            }else {
                throw new IllegalArgumentException("Заказ с номером " + orderNumber + " не найден");
            }

        } catch (Exception e) {
            logger.error("Ошибка при выдаче заказа с номером {}", orderNumber, e);
        }

    }

    @Override
    public void draw() {
        if(orders.isEmpty()){
            logger.warn("Очередь пуста");
            return;
        }else{
            logger.info("Текущее состояние очереди:");

            System.out.println("=============================");
            System.out.println("Номер | Имя клиента");
            System.out.println("=============================");
            for(Order order : orders){
                System.out.printf("%-6d| %s%n", order.getOrderNummer(), order.getCustomerName());
            }

        }

    }
}
