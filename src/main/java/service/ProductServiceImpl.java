package service;

import dao.ProductDAO;
import model.Product;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductServiceImpl implements ProductService, Serializable {

    @Inject
    private ProductDAO productDAO;

    public List<Product> getListOfProducts() {
        return productDAO.getListOfProducts();
    }

    @Override
    public Product getProductByID(Integer id) {
        return productDAO.getProductByID(id);
    }


}
