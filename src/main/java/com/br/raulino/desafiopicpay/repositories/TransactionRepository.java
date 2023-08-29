package com.br.raulino.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.raulino.desafiopicpay.domains.transaction.LocalTransaction;

public interface TransactionRepository extends JpaRepository<LocalTransaction, Long> {
    
}
