package com.br.raulino.desafiopicpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.raulino.desafiopicpay.domains.transaction.LocalTransaction;
import com.br.raulino.desafiopicpay.domains.transaction.TransactionDTO;
import com.br.raulino.desafiopicpay.domains.user.LocalUser;
import com.br.raulino.desafiopicpay.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transactionDTO) throws Exception{
        LocalUser sender = userService.findUserById(transactionDTO.senderId());
        LocalUser receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.amount());

        if (!authorizeTransaction()) {
            throw new Exception ("Transação não autorizada.");
        }

        LocalTransaction authorizedTransaction = new LocalTransaction();
        authorizedTransaction.setSender(sender);
        authorizedTransaction.setReceiver(receiver);
        authorizedTransaction.setAmount(transactionDTO.amount());
        authorizedTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.amount()));

        transactionRepository.save(authorizedTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    private boolean authorizeTransaction() {
        ResponseEntity<Map> authorizationReponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (authorizationReponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationReponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
