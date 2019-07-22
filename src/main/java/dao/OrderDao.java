package dao;

import model.Order;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order);
    void updateOrder(Order order);
    void removeOrder(Integer id);
    Order getOrderById(Integer id);
    List<Order> listOrders();
}
