package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
