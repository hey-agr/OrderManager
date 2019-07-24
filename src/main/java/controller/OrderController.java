package controller;

import model.Order;
import model.OrderContent;
import service.OrderService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class OrderController implements Serializable {

    private Order editOrder;
    private boolean newOrder = false;

    @Inject
    private OrderService orderService;

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

    public boolean isNewOrder() {
        return newOrder;
    }

    public String saveOrder() {
        if (newOrder) {
            addOrder(editOrder);
        } else {
            updateOrder(editOrder);
        }
        return "index.xhtml?faces-redirect=true";
    }

    public String editCurrentOrder(Order order) {
        newOrder = false;
        editOrder = order;
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String addNewOrder() {
        newOrder = true;
        editOrder = new Order();
        return "editOrder.xhtml";
    }

    public String cancel() {

        return "index.xhtml?faces-redirect=true";
    }

}
