package ir.digixo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"ir.digixo.discount", "ir.digixo.notification"})
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    public WebClient.Builder getWebClientBuilder() {
//        return WebClient.builder();
//    }
}
