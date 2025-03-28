package ir.digixo.product.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import ir.digixo.product.entity.Product;
import ir.digixo.product.ProductRequest;
import ir.digixo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    @Retry(name = "getProduct",fallbackMethod = "getProductFallBack")
    @RateLimiter(name = "getProduct" ,fallbackMethod = "getProductFallBackRateLimiter")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    public ResponseEntity<Product> getProductFallBack(@PathVariable("id") Long id, Throwable throwable) {
        log.info("getProductFallBack by id {}", id);
        return ResponseEntity.ok(new Product());
    }

    public ResponseEntity<Product> getProductFallBackRateLimiter(@PathVariable("id") Long id, Throwable throwable) {
        log.info("getProductFallBackRateLimiter by id {}", id);
        return ResponseEntity.ok(new Product());
    }
}
