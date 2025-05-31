package com.developer.generic_api_nosql.email.consumers;

import com.developer.generic_api_nosql.email.dtos.EmailDto;
import com.developer.generic_api_nosql.email.models.EmailModel;
import com.developer.generic_api_nosql.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDto EmailDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(EmailDto, emailModel);
        emailService.sendEmail(emailModel);
    }
}
