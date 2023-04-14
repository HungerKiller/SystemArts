package com.system.arts.controller;

import cn.hutool.jwt.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.arts.entity.Constant;
import com.system.arts.entity.User;
import com.system.arts.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(authenticationToken);

        String token = JWT.create()
                .setExpiresAt(new Date(System.currentTimeMillis() + (1000 * 30)))
                .setPayload("username", user.getUsername())
                .setKey(Constant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .sign();

        return objectMapper.writeValueAsString(token);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> checkCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current user roles: " + authentication.getAuthorities());

        if (authentication != null && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof String) {
                String username = (String) authentication.getPrincipal();
                System.out.println("Current user string: " + username);
                User user = userService.getUserByName(username);
                return ResponseEntity.ok(user);
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                String username = ((UserDetails) authentication.getPrincipal()).getUsername();
                System.out.println("Current user getPrincipal: " + username);
                User user = userService.getUserByName(username);
                return ResponseEntity.ok(user);
            }
        }

        return null;
    }
}
