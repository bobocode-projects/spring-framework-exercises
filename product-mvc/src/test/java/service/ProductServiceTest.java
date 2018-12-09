package service;

import exception.ProductMvcException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    ProductServiceInterface productService;

    @BeforeEach
    void init() {
        productService = new ProductService();
    }

    @AfterEach
    void tearDown() {
        productService = new ProductService();
    }

    @Test
    void findByInvalidId() {
        assertThrows(ProductMvcException.class, () -> productService.getById(10L));
    }
}