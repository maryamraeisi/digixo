package ir.digixo.discount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DISCOUNT")
public interface DiscountClient {

    @GetMapping("api/v1/discounts/{code}")
    ResponseEntity<Discount> getDiscount(@PathVariable("code") String code);
}
