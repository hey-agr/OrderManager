package controller;

import model.Product;
import service.ProductService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ConversationScoped
@Named
public class ProductController implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private ProductService productService;

    private String selectedProductID;
    private Product product;
    private List<Product> products;

    public void beginConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    public boolean isTransientConversation() {
        return conversation.isTransient();
    }

    public List<Product> getProducts() {
        if (products == null) {
            initializeProduct();
        }
        return products;
    }

    public String getSelectedProductID() {
        return selectedProductID;
    }

    public void setSelectedProductID(String selectedProductID) {
        this.selectedProductID = selectedProductID;
        if (!selectedProductID.isEmpty()) {
            Integer product_id = Integer.parseInt(selectedProductID);
            product = productService.getProductByID(product_id);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void initializeProduct() {
        products = productService.getListOfProducts();
    }
}
