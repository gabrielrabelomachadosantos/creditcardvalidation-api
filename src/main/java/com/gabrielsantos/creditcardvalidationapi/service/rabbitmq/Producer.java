package com.gabrielsantos.creditcardvalidationapi.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.creditcardvalidationapi.dto.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.queue.update-purchase-status}")
    private String updatePurchaseStatusQueue;

    @SneakyThrows
    public void sendToUpdatePurchaseStatusQueue(PurchaseDTO purchaseDTO) {
        rabbitTemplate.convertAndSend(updatePurchaseStatusQueue, objectMapper.writeValueAsString(purchaseDTO));
    }

}
