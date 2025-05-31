package com.developer.generic_api_nosql.email.services;

import com.developer.generic_api_nosql.email.enuns.StatusEmail;
import com.developer.generic_api_nosql.email.models.EmailModel;
import com.developer.generic_api_nosql.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDataTime(LocalDateTime.now());
            emailModel.setEmailFrom(this.emailFrom);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.emailFrom);
            simpleMailMessage.setTo(emailModel.getEmailTo());
            simpleMailMessage.setText(emailModel.getText());
            simpleMailMessage.setSubject(emailModel.getSubject());
            javaMailSender.send(simpleMailMessage);

            emailModel.setStatusEmail(StatusEmail.SENT);
            System.out.println("sendEmail");
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
        }
        return emailRepository.save(emailModel);
    }
}
