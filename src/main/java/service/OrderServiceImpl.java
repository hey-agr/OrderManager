package service;

import dao.OrderDao;
import model.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class OrderServiceImpl implements OrderService {
    @EJB
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeOrder(Integer id) {
        orderDao.removeOrder(id);
    }

    public Order getOrderById(Integer id) {
        return orderDao.getOrderById(id);
    }

    public List<Order> listOrders() {
        return orderDao.listOrders();
    }
}
