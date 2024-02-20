package ru.springgb.sem10HW.securyty.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.service.SessionService;
import ru.springgb.sem10HW.service.UserService;

@Service
public class CustomLogoutHandler implements LogoutHandler {
    private final SessionService sessionService;
    private final UserService userService;


    public CustomLogoutHandler(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {

        String userName = authentication.getName();
        User user = userService.findByEmail(userName);
        Long id = user.getSession().getId();
        sessionService.delete(id);

    }

}