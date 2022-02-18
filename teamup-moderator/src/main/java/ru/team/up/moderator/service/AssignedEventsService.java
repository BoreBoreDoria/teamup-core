package ru.team.up.moderator.service;

import ru.team.up.core.entity.AssignedEvents;
import ru.team.up.core.repositories.AssignedEventsRepository;

import java.util.List;

public interface AssignedEventsService {
    /**
     *
     * @param id
     * @return
     */
    AssignedEvents getAssignedEvent(Long id);

    AssignedEvents saveAssignedEvent(AssignedEvents assignedEvents);

    void removeAssignedEvent(Long id);
}
