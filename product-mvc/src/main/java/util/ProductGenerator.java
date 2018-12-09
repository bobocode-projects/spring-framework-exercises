package util;


import model.Product;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class ProductGenerator {

    public static Product getTestProduct() {
        return Product.builder()
                .name("Ram-Pap-Pou")
                .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextLong()))
                .build();
    }
}
