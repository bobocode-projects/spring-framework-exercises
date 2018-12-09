package service;

import exception.ProductMvcException;
import model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceInterface {

    Map<Long, Optional<Product>> productMap;

    public ProductService() {
        productMap = new HashMap<>();
    }

    @Override
    public void save(Product product) {
        long generateId = productMap.size() + 1;
        product.setId(generateId);
        productMap.put(generateId, Optional.of(product));
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> findProduct = productMap.get(id);
        return findProduct.orElseThrow(() -> {
            throw new ProductMvcException("No such element");
        });
    }

    @Override
    public List<Product> listAllProducts() {
        return productMap.values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
