package com.br.raulino.desafiopicpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.raulino.desafiopicpay.domains.user.LocalUser;

public interface UserRepository extends JpaRepository<LocalUser, Long> {
    Optional<LocalUser> findUserByDocument(String document);
    Optional<LocalUser> findUserById(Long id);
}
