package com.br.raulino.desafiopicpay.domains.transaction;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long senderId, Long receiverId) {
    
}
