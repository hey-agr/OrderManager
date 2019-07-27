package service;

import model.Order;
import model.OrderContent;

import java.util.List;

public interface OrderContentService {
    void addOrderContent(OrderContent orderContent);
    void updateOrderContent(OrderContent orderContent);
    void removeOrderContent(Integer id);
    OrderContent getOrderContentById(Integer id);
    List<OrderContent> getOrdersContent();
    List<OrderContent> getOrdersContentByOrder(Order order);
}
