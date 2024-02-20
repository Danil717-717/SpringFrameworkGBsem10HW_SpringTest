package ru.springgb.sem10HW.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.springgb.sem10HW.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase()
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepo_SaveAll_ReturnSavedUser(){
        //Arrange

        User user = new User(1L,"Us1Name","Us1LastName","Us1Email","Us1Password");
        //Act

        User saveUser = userRepository.save(user);
        //Assert
        assertNotNull(saveUser);
        assertThat(saveUser.getId()).isGreaterThan(0);
    }



}