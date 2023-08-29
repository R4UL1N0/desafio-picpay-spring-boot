package com.br.raulino.desafiopicpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.raulino.desafiopicpay.domains.user.LocalUser;
import com.br.raulino.desafiopicpay.dtos.UserDTO;
import com.br.raulino.desafiopicpay.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<LocalUser> createUser(@RequestBody UserDTO user) {
        LocalUser newUser = new LocalUser(user);
        return new ResponseEntity<LocalUser>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocalUser>> getAlUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
