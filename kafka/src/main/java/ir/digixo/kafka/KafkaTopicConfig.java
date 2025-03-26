package ir.digixo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    public NewTopic createTopic() {
        return TopicBuilder.name("product4")
                .partitions(3)
                .replicas(3)
                .configs(Map.of(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2"))
                .build();
    }
}