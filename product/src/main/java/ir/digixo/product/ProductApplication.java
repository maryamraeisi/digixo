package ir.digixo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"ir.digixo.discount"})
@ComponentScan(basePackages = {/*"ir.digixo.rabbitmq", */"ir.digixo.product", "ir.digixo.discount", "ir.digixo.kafka"})
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
