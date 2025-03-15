package ir.digixo.product.service;

import ir.digixo.discount.DiscountRequest;
import ir.digixo.discount.DiscountClient;
import ir.digixo.notification.NotificationClient;
import ir.digixo.notification.NotificationRequest;
import ir.digixo.product.entity.Product;
import ir.digixo.product.ProductRequest;
import ir.digixo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
//    private final WebClient.Builder builder;
    private final DiscountClient discountClient;
    private final NotificationClient notificationClient;

    public Product createProduct(ProductRequest productRequest) {
        BigDecimal price = calculatePrice(productRequest);
        Product product = new Product(productRequest, price);
        productRepository.save(product);
        sendNotification(product.getId());
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private BigDecimal calculatePrice(ProductRequest productRequest) {
        if (productRequest.getDiscountCode() == null) {
            return productRequest.getPrice();
        }
        return calculatePriceAfterDiscount(productRequest);
    }

    private BigDecimal calculatePriceAfterDiscount(ProductRequest productRequest) {

        // WebClient
//        Discount discount = builder.build()
//                .get()
//                .uri("http://DISCOUNT/api/v1/discounts/{code}", productRequest.discountCode())
//                .retrieve()
//                .bodyToMono(Discount.class)
//                .block();

        // OpenFeign
        DiscountRequest discountRequest = discountClient.getDiscount(productRequest.getDiscountCode()).getBody();

        BigDecimal payingPercentage = BigDecimal.valueOf(100.0 - discountRequest.getPercentage()).divide(new BigDecimal(100));
        BigDecimal finalPrice = productRequest.getPrice().multiply(payingPercentage);
        return finalPrice;
    }

    private void sendNotification(Long productId) {
        NotificationRequest notificationRequest = new NotificationRequest(productId, String.format("product with id %s is created.", productId));
        notificationClient.sendNotification(notificationRequest);
    }
}
