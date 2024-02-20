package ru.springgb.sem10HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.service.UserService;

import java.util.List;

@Controller
public class UserViewController {


    private final UserService userService;

    @Autowired
    public UserViewController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",userService.findAll());
        return "users_list";
    }
}
