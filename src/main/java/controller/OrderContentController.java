package controller;

import model.Order;
import model.OrderContent;
import service.OrderContentService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
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
