package ir.digixo.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NOTIFICATION")
public interface NotificationClient {

    @PostMapping("api/v1/notifications")
    ResponseEntity<Void> sendNotification(@RequestBody NotificationRequest notificationRequest);
}
