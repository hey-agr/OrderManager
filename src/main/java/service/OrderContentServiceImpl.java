package service;

import dao.OrderContentDAO;
import dao.OrderDAO;
import model.Order;
import model.OrderContent;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class OrderContentServiceImpl implements OrderContentService {

    @EJB
    private OrderContentDAO orderContentDAO;

    public void addOrderСontent(OrderContent orderContent) {
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

    public List<OrderContent> listOrdersContents() {
        return orderContentDAO.listOrdersContents();
    }

    public List<OrderContent> listOrdersContentByOrder(Order order) {
        return orderContentDAO.listOrdersContentByOrder(order);
    }
}
