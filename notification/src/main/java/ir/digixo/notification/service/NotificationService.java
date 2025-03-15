package ir.digixo.notification.service;

import ir.digixo.notification.NotificationRequest;
import ir.digixo.notification.entity.Notification;
import ir.digixo.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        Notification notification = new Notification(notificationRequest);
        notificationRepository.save(notification);
    }

}
