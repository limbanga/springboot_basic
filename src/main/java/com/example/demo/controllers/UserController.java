package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.RegisterRequest;
import com.example.demo.services.JwtUtils;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> createAuthenticationToken(
            @RequestBody @Valid LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            final UserDetails userDetails = userService
                    .loadUserByUsername(request.getUsername());

            return ResponseEntity.ok(jwtUtils.generateJwtToken((User) userDetails));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(e.getLocalizedMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(
             @RequestBody @Valid RegisterRequest request)
            throws CustomValidationException {
        userService.register(request);
        return ResponseEntity.ok().build();
    }
}