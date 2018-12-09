package service;

import model.Product;

import java.util.List;

public interface ProductServiceInterface {

    void save(Product product);

    Product getById(Long id);

    List<Product> listAllProducts();
}

