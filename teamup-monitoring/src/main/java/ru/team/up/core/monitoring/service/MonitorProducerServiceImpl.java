package ru.team.up.core.monitoring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.team.up.dto.*;
import ru.team.up.core.entity.*;
import ru.team.up.kafka.moderator.config.ProducerMonitoring;

import java.util.*;

@Slf4j
@Service
public class MonitorProducerServiceImpl implements MonitorProducerService {

    private ProducerMonitoring producer;

    @Override
    public ReportDto constructReportDto(Object principal, ControlDto control, Class cl, Map<String, Object> params) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        ReportDto reportDto = ReportDto.builder()
                .control(control)
                .reportName(cl.getSimpleName() + "/" + stackTrace[2].getMethodName())
                .reportStatus(ReportStatusDto.SUCCESS)
                .time(new Date())
                .parameters(params).build();

        if (principal.toString().equals("anonymousUser")) {
            reportDto.setInitiatorId(0L);
            reportDto.setInitiatorName("anonymousUser");
            reportDto.setInitiatorType(InitiatorTypeDto.USER);
        } else if (principal instanceof Admin) {
            Admin admin = (Admin) principal;
            reportDto.setInitiatorId(admin.getId());
            reportDto.setInitiatorName(admin.getUsername());
            reportDto.setInitiatorType(InitiatorTypeDto.ADMIN);
        } else if (principal instanceof User) {
            User user = (User) principal;
            reportDto.setInitiatorId(user.getId());
            reportDto.setInitiatorName(user.getUsername());
            reportDto.setInitiatorType(InitiatorTypeDto.USER);
        } else if (principal instanceof Moderator) {
            Moderator moderator = (Moderator) principal;
            reportDto.setInitiatorId(moderator.getId());
            reportDto.setInitiatorName(moderator.getUsername());
            reportDto.setInitiatorType(InitiatorTypeDto.MANAGER);
        } else {
            log.warn("Не удалось получить данные из объекта Principal");
        }

        String[] appModuleName = cl.getPackageName().split("\\.");
        switch (appModuleName[3]) {
            case ("app"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_APP);
                break;
            case ("auth"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_AUTH);
                break;
            case ("core"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_CORE);
                break;
            case ("external"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_EXTERNAL);
                break;
            case ("input"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_INPUT);
                break;
            case ("kafka"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_KAFKA);
                break;
            case ("moderator"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_MODERATOR);
                break;
            case ("monitoring"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_MONITORING);
                break;
            case ("sup"):
                reportDto.setAppModuleName(AppModuleNameDto.TEAMUP_SUP);
                break;
            default:
                log.warn("Не удалось определить модуль");
        }
        return reportDto;
    }

    @Override
    public void send(ReportDto content) {
        try {
            producer.send(content);
        } catch (JsonProcessingException e) {
            log.error("Не удалось отправить сообщение: {}. Ошибка: {}", e.getMessage(), e);
        }
    }
}
