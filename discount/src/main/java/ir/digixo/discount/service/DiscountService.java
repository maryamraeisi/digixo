package ir.digixo.discount.service;

import ir.digixo.discount.entity.Discount;
import ir.digixo.discount.DiscountRequest;
import ir.digixo.discount.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {

    private final DiscountRepository discountRepository;

    public Discount createDiscount(DiscountRequest discountRequest) {
        Discount discount = new Discount(discountRequest);
        return discountRepository.save(discount);
    }

    public Discount getDiscountByCode(String code) {
        log.info("Get discount by code {}", code);
        return discountRepository.findByCode(code).orElse(new Discount());
    }
}
