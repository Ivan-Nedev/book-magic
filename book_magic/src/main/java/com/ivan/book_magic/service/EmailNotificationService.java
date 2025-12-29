package com.ivan.book_magic.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNewBookNotification(String to,
                                        String authorName,
                                        String bookTitle,
                                        String imageUrl,
                                        String productUrl) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Нова книга от " + authorName);
        message.setText(
                "Открита е нова книга:\n\n" +
                "Автор: " + authorName + "\n" +
                "Заглавие: " + bookTitle + "\n" +
                "Изображение: " + imageUrl + "\n" +
                "Линк: "  + productUrl

        );

        mailSender.send(message);
    }
}
