package ir.digixo.product.entity;

import ir.digixo.product.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product(ProductRequest productRequest, BigDecimal price) {
        this.name = productRequest.name();
        this.description = productRequest.description();
        this.price = price;
    }
}
