package ir.digixo.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(String exchange, String routingKey, Object data) {
        amqpTemplate.convertAndSend(exchange, routingKey, data);
        log.warn("=======> published to {} by routing key {} with data {}", exchange, routingKey, data);

    }
}