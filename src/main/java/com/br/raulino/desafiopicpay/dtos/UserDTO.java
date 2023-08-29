package com.br.raulino.desafiopicpay.dtos;

import java.math.BigDecimal;

import com.br.raulino.desafiopicpay.domains.user.UserType;


public record UserDTO(String firstName, String lastName, String documentId, BigDecimal balance, String email, String password, UserType userType) {
    
}
