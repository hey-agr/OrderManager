package controller;

import model.Order;
import service.OrderService;


import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ConversationScoped
@Named
public class OrderController implements Serializable{

    @Inject
    private Conversation conversation;

    @Inject
    private OrderService orderService;

    private List<Order> orders;

    private Order order;
    private Integer orderId;

    public void beginConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    public boolean isTransientConversation() {
        return conversation.isTransient();
    }

    public List<Order> getOrders() {
        if (orders == null) {
            refreshOrders();
        }
        return orders;
    }

    public Order getOrder() {
        if (order == null) {
            initializeOrder();
        }
        return order;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    public String addNewOrder() {
        return "editOrder.xhtml";
    }

    public void removeCurrentOrder(Integer id) {
        orderService.removeOrder(id);
        refreshOrders();
    }

    public void refreshOrders() {
        orders = orderService.getListOfOrders();
    }

    public String editCurrentOrder(Order order) {
        orderId = order.getId();
        this.order = order;
        return "editOrder.xhtml";
    }

    public String saveOrder() {
        if (orderId == null) {
            orderService.addOrder(order);
        } else {
            orderService.updateOrder(order);
        }
        return "index.xhtml?faces-redirect=true";
    }

    public void initializeOrder() {
        if (orderId == null) {
            order = new Order();
            return;
        }
        order = orderService.getOrderById(orderId);
    }

    public void setOrderSum(BigDecimal sum) {
        order.setOrderSum(sum);
    }

    public String getFormattedOrderDate(Date orderDate) {
        if (orderDate == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy.MM.dd");
        return dateFormat.format(orderDate);
    }
}
