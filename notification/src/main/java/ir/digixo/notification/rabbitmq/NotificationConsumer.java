package ir.digixo.notification.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.digixo.notification.NotificationRequest;
import ir.digixo.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

//    @RabbitListener(queues = "${rabbitmq.queue}")
//    public void consume(Message message) throws IOException {
//        NotificationRequest notificationRequest = new ObjectMapper().readValue(message.getBody(), NotificationRequest.class);
//        log.warn("========> consumed {} from queue", notificationRequest);
//        notificationService.sendNotification(notificationRequest);
//    }
}
