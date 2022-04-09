package ru.team.up.sup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.team.up.dto.AppModuleNameDto;
import ru.team.up.dto.SupParameterDto;
import ru.team.up.kafka.moderator.config.ProducerSup;

@Slf4j
@Service
public class KafkaSupServiceImpl implements KafkaSupService {

    ProducerSup producer;

    @Override
    public void getAllModuleParameters() {
        producer.send(AppModuleNameDto.TEAMUP_SUP, AppModuleNameDto.TEAMUP_CORE);
    }
}