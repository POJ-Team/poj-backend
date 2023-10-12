package com.poj.service.email;

import com.poj.exception.email.EmailSendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSendService {
    private final JavaMailSender emailSender;

    public void sendEmail(String toEmail, String title, String context) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, context);
        try {
            emailSender.send(emailForm);
        } catch (Exception e) {
            throw new EmailSendException(toEmail, title, context);
        }
    }

    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);
        return message;
    }
}
