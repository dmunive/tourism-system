package com.tourism.service;

import com.tourism.domain.dto.CheckInDto;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.errors.SendEmailFailException;
import com.tourism.util.MailMessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailService {

    private final JavaMailSender emailSender;
    private final MailMessageProperties mailMessageProperties;
    public MailService(JavaMailSender emailSender,
                       MailMessageProperties mailMessageProperties) {
        this.emailSender = emailSender;
        this.mailMessageProperties = mailMessageProperties;
    }

    public void sendErrorMessage(OwnerDto owner) {
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            String body = String.format(mailMessageProperties.getErrorBody(), owner.getName(),
                    owner.getLastName());

            message.setFrom(mailMessageProperties.getFrom());
            message.setTo(owner.getEmail());
            message.setSubject(mailMessageProperties.getErrorSubject());
            message.setText(body);
            emailSender.send(message);
        } catch (Exception e) {
            throw new SendEmailFailException();
        }
    }

    public void sendSuccessfulMessage(CheckInDto checkIn) {
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            String body = String.format(mailMessageProperties.getSuccessBody(), checkIn.getOwner().getName(),
                checkIn.getOwner().getLastName(), checkIn.getFromDate().toString(), checkIn.getToDate().toString());

            message.setFrom(mailMessageProperties.getFrom());
            message.setTo(checkIn.getOwner().getEmail());
            message.setSubject(mailMessageProperties.getSuccessSubject());
            message.setText(body);
            emailSender.send(message);
        } catch (Exception e) {
            throw new SendEmailFailException();
        }
    }
}
