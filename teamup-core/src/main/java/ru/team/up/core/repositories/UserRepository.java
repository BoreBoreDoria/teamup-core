package ru.team.up.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.up.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
