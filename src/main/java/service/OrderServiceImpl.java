package service;

import dao.OrderDAO;
import model.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class OrderServiceImpl implements OrderService {
    @EJB
    private OrderDAO orderDao;

    public void setOrderDao(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }

    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

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
