package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getListOfProducts();
    Product getProductByID(Integer id);
}
