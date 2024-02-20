package ru.springgb.sem10HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.service.UserService;

@Controller
public class SignInController {

    private final UserService userService;

    @Autowired
    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String getRegistUsers(){
        return "signIn_user";
    }


//    @PostMapping("/signIn")
//    public String registerUser(User user){
//        userService.save(user);
//        return "users_list";
//    }
}
