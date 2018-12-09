package web.controller;


import exception.ProductMvcException;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.ProductServiceInterface;
import util.ProductGenerator;
import validation.ProductValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductServiceInterface productService;

    ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id, Model model) {
        try {
            Product product = productService.getById(id);
            model.addAttribute("product", product);
            return "product";
        } catch (NullPointerException e) {
            return "error";
        }
    }

    @GetMapping("/list")
    public String listAllProducts(Model model) {
        List<Product> productList = productService.listAllProducts();
        model.addAttribute(productList);
        return "list";
    }

    @GetMapping("/add")
    public String addNewProduct() {
        return "add_product";
    }

    @PostMapping
    public String post(@ModelAttribute("product") Product product, Model model, BindingResult result) {
        ProductValidator productValidator = new ProductValidator();
        productValidator.validate(product, result);
        if (result.hasErrors()) {
            return "add_product";
        } else {
            productService.save(product);
            return "add_product";
        }
    }

}
