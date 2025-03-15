package ir.digixo.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/product-fallback")
    public String productFallback() {
        return "Product service is down";
    }

    @RequestMapping("/discount-fallback")
    public String discountFallback() {
        return "discount service is down";
    }
}
