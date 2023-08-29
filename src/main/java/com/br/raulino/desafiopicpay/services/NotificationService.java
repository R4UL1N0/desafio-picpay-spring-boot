package com.br.raulino.desafiopicpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.raulino.desafiopicpay.domains.user.LocalUser;
import com.br.raulino.desafiopicpay.dtos.NotificationDTO;

@Service
public class NotificationService {
    
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(LocalUser user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
        
        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Erro no envio da notificação");
            throw new Exception("Serviço de notificação fora do ar.");
        }
    }
}
