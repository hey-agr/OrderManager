package controller;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ApplicationController implements Serializable {
    @Inject
    OrderController orderController;

    @Inject
    OrderContentController orderContentController;

    @Inject
    ProductController productController;

    public void preRenderViewEvent() {
        if (orderController.isTransientConversation()){
            orderController.beginConversation();
        }

        if (!orderContentController.isTransientConversation()){
            orderContentController.endConversation();
        }

        if (!productController.isTransientConversation()){
            productController.endConversation();
        }
    }

    public void preRenderViewOrderEvent() {
        if (orderContentController.isTransientConversation()) {
            orderContentController.beginConversation();
        }
    }

    public void preRenderViewOrderContentEvent() {
        if (!productController.isTransientConversation()) {
            productController.endConversation();
        }
        productController.beginConversation();

        productController.initializeProduct();
    }

    public String saveData() {
        orderController.saveOrder();
        orderContentController.saveOrderContent();
        return "index.xhtml?faces-redirect=true";
    }

    public String cancelDataEdit() {
        return "index.xhtml?faces-redirect=true";
    }

}
