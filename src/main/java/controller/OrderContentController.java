package controller;

import model.Order;
import model.OrderContent;
import service.OrderContentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class OrderContentController implements Serializable {

    @EJB
    private OrderContentService orderContentService;

    public void setOrderContentService(OrderContentService orderContentService) {
        this.orderContentService = orderContentService;
    }

    public List<OrderContent> listOrderContentByOrder(Order order) {
        return orderContentService.listOrdersContentByOrder(order);
    }

}
