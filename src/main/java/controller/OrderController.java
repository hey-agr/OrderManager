package controller;

import model.Order;
import service.OrderService;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class OrderController {

    @EJB
    private OrderService orderService;

    private Order editOrder;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public Order getEditOrder() {
        return editOrder;
    }

    public void setEditOrder(Order editOrder) {
        this.editOrder = editOrder;
    }

    public List<Order> listOrders() {
        return orderService.listOrders();
    }

    public void removeOrder(Integer id) {
        orderService.removeOrder(id);
    }

    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    public void addOrder(Order order) {
        orderService.addOrder(order);
    }

    public String saveOrder() {
        if (editOrder.getId() == null) {
            addOrder(editOrder);
        } else {
            updateOrder(editOrder);
        }
        return "index.xhtml?faces-redirect=true";
    }

    public String editCurrentOrder(Order order) {
        editOrder = order;
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String addNewOrder() {
        editOrder = new Order();
        return "editOrder.xhtml?faces-redirect=true";
    }

}
