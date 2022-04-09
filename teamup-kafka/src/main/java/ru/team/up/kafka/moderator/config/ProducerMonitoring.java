package ru.team.up.kafka.moderator.config;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.team.up.dto.ReportDto;

@Component
@Slf4j
public class ProducerMonitoring {

    private final KafkaTemplate<String, ReportDto> kafkaTemplate;

    @Value("34wx3onr-moderator")
    private String topic;

    ProducerMonitoring(KafkaTemplate<String, ReportDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ReportDto message) throws JsonProcessingException {
        this.kafkaTemplate.send(topic, message);
        log.debug("Отправили сообщение [" + message + "] to " + topic);
    }

}
