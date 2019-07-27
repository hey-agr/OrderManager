package controller;

import model.OrderContent;
import model.Product;
import service.OrderContentService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ConversationScoped
@Named
public class OrderContentController implements Serializable {
    @Inject
    private Conversation conversation;

    @Inject
    private OrderContentService orderContentService;

    @Inject
    private OrderController orderController;

    @Inject
    private ProductController productController;

    private OrderContent orderContent;
    private Integer orderContentId;
    private List<OrderContent> currentOrderContent;
    private List<OrderContent> deletedOrderContent;

    public void beginConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    public boolean isTransientConversation() {
        return conversation.isTransient();
    }

    public OrderContent getOrderContent() {
        if (orderContent == null) {
            initializeOrderContent();
        }
        return orderContent;
    }

    public void setOrderContent(OrderContent orderContent) {
        this.orderContent = orderContent;
    }

    public List<OrderContent> getCurrentOrderContent() {
        if (currentOrderContent == null) {
            refreshCurrentOrderContent();
        }
        return currentOrderContent;
    }

    public Integer getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(Integer orderContentId) {
        this.orderContentId = orderContentId;
    }

    public void saveOrderContent() {
        //ADD, UPDATE ORDER CONTENT
        for (OrderContent content:currentOrderContent) {
            if (content.getId() == null) {
                orderContentService.addOrderContent(content);
            } else {
                orderContentService.updateOrderContent(content);
            }
        }
        //REMOVE ORDER CONTENT
        for (OrderContent content:deletedOrderContent
             ) {
            if (content.getId() != null) {
                orderContentService.removeOrderContent(content.getId());
            }
        }
    }

    public String addNewOrderContent() {
        initializeOrderContent();
        return "editOrderContent.xhtml?faces-redirect=true";
    }

    public void removeOrderContent(OrderContent orderContent) {
        deletedOrderContent.add(orderContent);
        currentOrderContent.remove(orderContent);
    }

    public String addOrderContent() {
        orderContent.setOrder(orderController.getOrder());
        orderContent.setProduct(productController.getProduct());
        currentOrderContent.add(orderContent);
        return "editOrder.xhtml?faces-redirect=true";
    }

    public String cancelOrderContentEdit() {
        orderContent = null;
        return "editOrder.xhtml?faces-redirect=true";
    }

    public void refreshCurrentOrderContent() {
        currentOrderContent = orderContentService.getOrdersContentByOrder(orderController.getOrder());
        deletedOrderContent = new ArrayList<>();
    }

    public void initializeOrderContent() {
        if (orderContentId == null) {
            orderContent = new OrderContent();
        } else {
            orderContent = orderContentService.getOrderContentById(orderContentId);
        }
    }

}
