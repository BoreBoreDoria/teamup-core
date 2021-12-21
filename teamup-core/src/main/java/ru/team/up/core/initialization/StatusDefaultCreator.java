package ru.team.up.core.initialization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.team.up.core.entity.Status;
import ru.team.up.core.repositories.StatusRepository;

import javax.transaction.Transactional;


@Component
@Transactional
public class StatusDefaultCreator {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusDefaultCreator(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Bean("StatusDefaultCreator")
    public void statusDefaultCreator() {
        statusRepository.save(Status.builder()
                .id(1L)
                .status("Проверено")
                .build());

        statusRepository.save(Status.builder()
                .id(2L)
                .status("На проверке")
                .build());

        statusRepository.save(Status.builder()
                .id(3L)
                .status("Завершено")
                .build());
    }
}