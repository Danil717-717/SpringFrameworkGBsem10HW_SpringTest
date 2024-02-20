package ru.springgb.sem10HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.springgb.sem10HW.model.Session;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.repository.SessionRepository;
import ru.springgb.sem10HW.service.UserService;

import static ru.springgb.sem10HW.model.Role.USER;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final SessionRepository sessionRepository;

    private final PasswordEncoder passwordEncoder;



    @Autowired
    public RegistrationController(UserService userService, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/regist")
    public String getRegistUsers() {
        return "registrUser";
    }


    @PostMapping("/regist")
    public String registerUser(User user) {
        Session session = new Session("newSession");
        sessionRepository.save(session);

        user.setHashPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(USER);
        user.addSession(session);
        userService.save(user);

        return "signIn_user";
    }
}
