package ir.digixo.discount.controller;

import ir.digixo.discount.entity.Discount;
import ir.digixo.discount.dto.DiscountRequest;
import ir.digixo.discount.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountRequest discountRequest) {
        return ResponseEntity.ok(discountService.createDiscount(discountRequest));
    }

    @GetMapping("{code}")
    public ResponseEntity<Discount> getDiscount(@PathVariable String code) {
        return ResponseEntity.ok(discountService.getDiscountByCode(code));
    }

}
