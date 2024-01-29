package com.lhduc.notificationservice.streaming.listener;

import com.lhduc.notificationservice.core.contant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(id = KafkaConstant.NOTIFICATION_GROUP_ID, topics = KafkaConstant.NOTIFICATION_TOPIC)
public class MultiTypeKafkaListener {
    @KafkaHandler
    public void handleNotification(Object event) {
        log.info("Received Notification from Order - {}", event);
    }
}
