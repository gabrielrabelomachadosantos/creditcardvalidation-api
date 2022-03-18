package com.gabrielsantos.creditcardvalidationapi.service.rabbitmq;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.creditcardvalidationapi.dto.PurchaseDTO;
import com.gabrielsantos.creditcardvalidationapi.enums.PurchaseStatus;
import com.gabrielsantos.creditcardvalidationapi.exception.BadRequestException;
import com.gabrielsantos.creditcardvalidationapi.exception.InvalidCreditCardException;
import com.gabrielsantos.creditcardvalidationapi.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Consumer {

    private final ObjectMapper objectMapper;

    private final CreditCardService creditCardService;

    private final Producer producer;

    @SneakyThrows
    @RabbitListener(queues = {"${spring.rabbitmq.queue.pending-purchase}"})
    public void consumerPendingPurchase(@Payload Message message) {
        PurchaseDTO purchaseDTO = objectMapper
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(message.getBody(), PurchaseDTO.class);

        try {
            creditCardService.validateCreditCard(purchaseDTO.getCreditCardDTO()).getStatusCode().equals(HttpStatus.OK);

            purchaseDTO.setPurchaseStatus(PurchaseStatus.APPROVED);

            producer.sendToUpdatePurchaseStatusQueue(purchaseDTO);
        } catch(InvalidCreditCardException | BadRequestException exception) {
            purchaseDTO.setPurchaseStatus(PurchaseStatus.REPROVED);

            producer.sendToUpdatePurchaseStatusQueue(purchaseDTO);
        }
    }

}
