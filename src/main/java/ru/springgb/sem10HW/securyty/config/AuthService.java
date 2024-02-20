package ru.springgb.sem10HW.securyty.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.springgb.sem10HW.model.User;
import ru.springgb.sem10HW.repository.UserRepository;
import ru.springgb.sem10HW.securyty.details.UserDetailsImpl;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean authenticate(User user) {
        boolean log = login(user.getEmail(), user.getPassword());
        boolean reg = register(user);
        boolean logout = logout(user.getId());

        if (log && reg && logout) {
            return true;
        } else
            throw new UsernameNotFoundException("User not found");


    }

    private boolean login(String username, String password) {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return true;
        } else return false;
    }


    private boolean register(User user) {
        Optional<User> user1 = userRepository.findByName(user.getName());

        if (user1.isPresent()) {
            return true;
        } else throw new UsernameNotFoundException("User not found");
    }

    private boolean logout(Long userId) {
        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isPresent()) {
            return true;
        } else throw new UsernameNotFoundException("User not found");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (authenticate(user.get())) {
            return new UserDetailsImpl(user.get());
        } else
            throw new UsernameNotFoundException("User not found");

    }
}
