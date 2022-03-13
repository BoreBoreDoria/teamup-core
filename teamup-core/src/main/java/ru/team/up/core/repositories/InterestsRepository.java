package ru.team.up.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.up.core.entity.Account;
import ru.team.up.core.entity.Interests;

import java.util.List;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, Long> {

    Interests getInterestsById(Long id);
}
