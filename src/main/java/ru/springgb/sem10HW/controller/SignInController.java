package ru.springgb.sem10HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.springgb.sem10HW.service.UserService;

@Controller
public class SignInController {

    @Autowired
    private final UserService userService;

    @Autowired
    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String getRegistUsers(){
        return "signIn_user";
    }

}
