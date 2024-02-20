package ru.springgb.sem10HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springgb.sem10HW.model.Session;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
