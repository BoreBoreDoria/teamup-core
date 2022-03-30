package ru.team.up.core.monitoring.service;

import ru.team.up.dto.*;

import java.util.Map;

public interface MonitorProducerService {
    Map<String,Object> parameters(String key, Object value);

    ReportDto constructReportDto(Object principal, ControlDto control,
                                 String reportName, AppModuleNameDto appModuleName,
                                 ReportStatusDto reportStatusDto,
                                 String param1, Object param2);

    void send(ReportDto content);
}
