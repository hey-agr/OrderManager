package controller;

import model.Order;
import model.OrderContent;
import model.Product;
import service.OrderContentService;
import service.OrderService;
import service.ProductService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ConversationScoped
@Named
public class EditOrderController implements Serializable {

    @Inject
    OrderService orderService;

    @Inject
    OrderContentService orderContentService;

    @Inject
    ProductService productService;

    @Inject
    private Conversation conversation;

    private Order order;
    private Integer orderId;
    private OrderContent orderContent;
    private Integer orderContentId;
    private String selectedProductID;
    private List<OrderContent> orderContents;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderContent> getOrderContents() {
        return orderContents;
    }

    public OrderContent getOrderContent() {
        return orderContent;
    }

    public Integer getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(Integer orderContentId) {
        this.orderContentId = orderContentId;
    }

    public String getSelectedProductID() {
        return selectedProductID;
    }

    public void setSelectedProductID(String selectedProductID) {
        this.selectedProductID = selectedProductID;
    }

    public String addNewOrder() {
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String editCurrentOrder() {
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        if (orderId == null) {
            orderService.addOrder(order);
        } else {
            orderService.updateOrder(order);
        }

        for (OrderContent content:orderContents
             ) {
            if (content.getId() == null) {
                orderContentService.addOrderContent(content);
            } else {
                orderContentService.updateOrderContent(content);
            }
        }

        conversation.end();
        return "index.xhtml?faces-redirect=true";
    }

    public String cancelOrderEdit() {
        conversation.end();
        return "index.xhtml?faces-redirect=true";
    }

    public String addNewOrderContent() {
        return "editOrderContent.xhtml?faces-redirect=true";
    }

    public String saveOrderContent() {
        if (!selectedProductID.isEmpty()) {
            Integer product_id = Integer.parseInt(selectedProductID);
            Product product = productService.getProductByID(product_id);
            orderContent.setProduct(product);
        }
        orderContent.setOrder(order);
        orderContents.add(orderContent);
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String cancelOrderContentEdit() {
        orderContent = null;
        return "editOrder.xhtml?faces-redirect=true";
    }

    public void preRenderViewOrderEvent() {
        if (!conversation.isTransient()){
            conversation.end();
        }
        conversation.begin();

        if (order == null) {
            initializeOrder();
        }
    }

    private void initializeOrder() {
        if (orderId == null) {
            order = new Order();
            orderContents = new ArrayList<>();
            return;
        }
        order = orderService.getOrderById(orderId);
        orderContents = orderContentService.getOrdersContentByOrder(order);
    }

    public void preRenderViewOrderContentEvent() {
        if (orderContent == null) {
            initializeOrderContent();
        }
    }

    private void initializeOrderContent() {
        if (orderContentId == null) {
            orderContent = new OrderContent();
            return;
        }
        orderContent = orderContentService.getOrderContentById(orderContentId);
    }

    public void removeCurrentOrderContent(Integer id) {
        orderContentService.removeOrderContent(id);
    }
}
