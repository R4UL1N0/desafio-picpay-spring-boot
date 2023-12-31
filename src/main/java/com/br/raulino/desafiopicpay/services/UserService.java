package com.br.raulino.desafiopicpay.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.raulino.desafiopicpay.domains.user.LocalUser;
import com.br.raulino.desafiopicpay.domains.user.UserType;
import com.br.raulino.desafiopicpay.dtos.UserDTO;
import com.br.raulino.desafiopicpay.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(LocalUser sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Lojistas não podem realizar pagamentos.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public List<LocalUser> getAllUsers() {
        return userRepository.findAll();
    }

    public LocalUser findUserById(Long id) throws Exception{
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(LocalUser user) {
        userRepository.save(user);
    }

    public LocalUser createUser(UserDTO data) throws Exception {
        if (userRepository.findUserByDocument(data.documentId()).isPresent()) {
            throw new Exception("User already exists");
        }
        LocalUser newUser = new LocalUser(data);
        saveUser(newUser);

        return newUser;
    }
}
