package service;

import dao.OrderContentDAO;
import dao.OrderDAO;
import model.Order;
import model.OrderContent;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class OrderContentServiceImpl implements OrderContentService, Serializable {

    @Inject
    private OrderContentDAO orderContentDAO;

    public void addOrderContent(OrderContent orderContent) {
        orderContentDAO.addOrderСontent(orderContent);
    }

    public void updateOrderContent(OrderContent orderContent) {
        orderContentDAO.updateOrderContent(orderContent);
    }

    public void removeOrderContent(Integer id) {
        orderContentDAO.removeOrderContent(id);
    }

    public OrderContent getOrderContentById(Integer id) {
        return orderContentDAO.getOrderContentById(id);
    }

    public List<OrderContent> getOrdersContent() {
        return orderContentDAO.getOrdersContent();
    }

    public List<OrderContent> getOrdersContentByOrder(Order order) {
        return orderContentDAO.getOrdersContentByOrder(order);
    }
}
