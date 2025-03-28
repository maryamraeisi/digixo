package ir.digixo.product.service;

import ir.digixo.discount.DiscountRequest;
import ir.digixo.discount.DiscountClient;
import ir.digixo.notification.NotificationClient;
import ir.digixo.notification.NotificationRequest;
import ir.digixo.product.entity.Product;
import ir.digixo.product.ProductRequest;
import ir.digixo.product.repository.ProductRepository;
//import ir.digixo.rabbitmq.producer.RabbitMQMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
//    private final WebClient.Builder builder;
    private final DiscountClient discountClient;

//    private final NotificationClient notificationClient; //changed to event driven style

//    private final RabbitMQMessageProducer rabbitMQMessageProducer; //changed to using kafka
//    @Value(value = "${rabbitmq.exchange}")
//    private String PRODUCT_EXCHANGE;
//    @Value(value = "${rabbitmq.routing-key}")
//    private String PRODUCT_NOTIFICATION_ROUTING_KEY;

    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;

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

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
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
//        notificationClient.sendNotification(notificationRequest);

//        rabbitMQMessageProducer.publish(PRODUCT_EXCHANGE, PRODUCT_NOTIFICATION_ROUTING_KEY, notificationRequest);

        kafkaTemplate.send("product4", notificationRequest);
    }

}
