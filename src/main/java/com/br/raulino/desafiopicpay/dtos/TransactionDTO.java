package com.br.raulino.desafiopicpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long senderId, Long receiverId) {
    
}
