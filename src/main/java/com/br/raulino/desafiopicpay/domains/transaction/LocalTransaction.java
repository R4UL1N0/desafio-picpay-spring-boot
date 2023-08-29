package com.br.raulino.desafiopicpay.domains.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.raulino.desafiopicpay.domains.user.LocalUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_transaction")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of="id")
public class LocalTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private LocalUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private LocalUser receiver;

    private BigDecimal amount;

    private LocalDateTime timestamp;
}
