package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public void register(RegisterRequest request, Role role)
            throws CustomValidationException {
        // check username existed
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new CustomValidationException("username", "Username is already taken");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
    }

    public void register(RegisterRequest request)
            throws CustomValidationException {
        register(request, Role.USER);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}