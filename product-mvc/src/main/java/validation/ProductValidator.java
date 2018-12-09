package validation;

import model.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if(product.getName() == null || product.getName().length() <= 2) {
            errors.rejectValue("name", "Name is null or length less than 2");
        }
        if(product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("price", "Price can't be 0 or less");
        }
    }
}
