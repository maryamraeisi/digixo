package ir.digixo.notification.kafka;

import ir.digixo.notification.NotificationRequest;
import ir.digixo.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(containerFactory = "factory", topics = "product4", groupId = "1")
public class KafkaNotificationConsumer {

    private final NotificationService notificationService;

    @KafkaHandler
    void handler(NotificationRequest notificationRequest) {
        notificationService.sendNotification(notificationRequest);
        log.warn("====>Consumed {} from Queue",notificationRequest);
    }

}