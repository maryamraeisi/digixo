package ir.digixo.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    private Long id;
    private String code;
    private float percentage;
    private LocalDate expirationDate;
}
