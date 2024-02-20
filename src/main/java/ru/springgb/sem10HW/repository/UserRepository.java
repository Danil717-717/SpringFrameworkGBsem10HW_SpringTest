package ru.springgb.sem10HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.springgb.sem10HW.model.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findByHashPassword(@Param("hashPassword") String hashPassword);
    Optional<User> findByName(@Param("name") String name);

}
