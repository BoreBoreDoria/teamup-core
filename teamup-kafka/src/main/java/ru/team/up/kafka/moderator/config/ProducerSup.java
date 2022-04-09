package ru.team.up.kafka.moderator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.team.up.dto.AppModuleNameDto;

@Component
@Slf4j
public class ProducerSup {

    private final KafkaTemplate<AppModuleNameDto, AppModuleNameDto> kafkaTemplate;


    @Value("34wx3onr-moderator")
    private String topic;

    ProducerSup(KafkaTemplate<AppModuleNameDto, AppModuleNameDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AppModuleNameDto message) {
        this.kafkaTemplate.send(topic, message);
        log.debug("Отправили сообщение [" + message + "] to " + topic);
    }

    public void send(AppModuleNameDto teamupSup, AppModuleNameDto systemName) {
        this.kafkaTemplate.send(topic, teamupSup, systemName);
    }
}
