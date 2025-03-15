package ir.digixo.discount.entity;

import ir.digixo.discount.dto.DiscountRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "percentage", nullable = false)
    private float percentage;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    public Discount(DiscountRequest discountRequest) {
        this.code = discountRequest.code();
        this.percentage = discountRequest.percentage();
        this.expirationDate = LocalDate.parse(discountRequest.expirationDate());
    }
}
