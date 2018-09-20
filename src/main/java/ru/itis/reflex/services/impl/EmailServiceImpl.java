package ru.itis.reflex.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.services.interfaces.EmailService;
import ru.itis.reflex.services.interfaces.KeyService;


@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private KeyService keyService;

    @Override
    @Transactional
    public void sendEmail(String emails) {
        String[] emailsArr = emails.trim().split(" ");
        for(String email : emailsArr) {
            Key key = keyService.getKeyByEmail(email);

            MimeMessagePreparator messagePreparator = mimeMessage -> {

                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("ReFlex");
                messageHelper.setTo(email);
                messageHelper.setSubject("Регистрация");
                messageHelper.setText("Ваш код регистрации: " + key.getValue(), true);

            };

            try {
                this.javaMailSender.send(messagePreparator);
            } catch (MailException e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

}
