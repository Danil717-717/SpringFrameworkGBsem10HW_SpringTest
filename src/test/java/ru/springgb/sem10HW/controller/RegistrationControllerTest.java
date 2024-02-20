package ru.springgb.sem10HW.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.springgb.sem10HW.model.Session;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.repository.SessionRepository;
import ru.springgb.sem10HW.repository.UserRepository;
import ru.springgb.sem10HW.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;


@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private MockMvc mock;

    @InjectMocks
    private UserControllerImpl userController;
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testUserRepo_GetSession_ReturnSession(){
        User user = new User(1L,"Us1Name","Us1LastName","Us1Email","Us1Password");

        Session session = new Session(1L,"NameSession");
        sessionRepository.save(session);
        Long idSession = 1L;

        user.addSession(session);
        userRepository.save(user);
        Long id= user.getId();

        when(userService.findById(id)).thenReturn(user);
        when(sessionRepository.findById(idSession)).thenReturn(Optional.of(session));

        User foundUser =  userService.findById(id);
        Optional<Session> foundSession = sessionRepository.findById(idSession);

        assertThat(foundUser).isNotNull();
        assertThat(foundSession).isNotNull();
        assertEquals(id, foundUser.getId());
        assertEquals(idSession, user.getSession().getId());
    }

    @Test
    public void testLogout_DellSession() throws Exception {
        User user = new User(1L,"Us1Name","Us1LastName","Us1Email","Us1Password");

        Session session = new Session(1L,"NameSession");
        sessionRepository.save(session);
        Long idSession = 1L;

        user.addSession(session);
        userRepository.save(user);
        Long id= user.getId();

        when(userService.findById(id)).thenReturn(user);
        when(sessionRepository.findById(idSession)).thenReturn(Optional.of(session));

        User foundUser =  userService.findById(id);
        Optional<Session> foundSession = sessionRepository.findById(idSession);

        assertThat(foundUser).isNotNull();
        assertThat(foundSession).isNotNull();
        assertEquals(id, foundUser.getId());
        assertEquals(idSession, user.getSession().getId());

        mock.perform(logout());
        assertThat(foundSession).isNull();

    }




}