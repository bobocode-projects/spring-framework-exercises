package model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;


    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
