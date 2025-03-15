package ir.digixo.notification.entity;

import ir.digixo.notification.NotificationRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long toProductId;
    private String message;
    private LocalDateTime sendAt;

    public Notification(NotificationRequest notificationRequest) {
        this.toProductId = notificationRequest.getToProductId();
        this.message = notificationRequest.getMessage();
        this.sendAt = LocalDateTime.now();
    }
}
