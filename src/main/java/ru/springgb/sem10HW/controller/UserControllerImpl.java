package ru.springgb.sem10HW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {
    private final UserService service;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<User> getAllUser() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

    @Override
    @GetMapping("/request")
    public User getUserByEmail(@RequestParam("email") String email) {
        return service.findByEmail(email);
    }

    
}
