package web.controller;

import lombok.Getter;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProductService;
import util.ProductGenerator;

import java.math.BigDecimal;

@Controller
@RequestMapping("/test_product")
public class TestProductController {

    ProductService productService;

    TestProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping
    public String getTestProduct(Model model) {
        productService.save(new Product("SingleProduct", BigDecimal.ONE));
        return "Test product was saved";
    }
}
