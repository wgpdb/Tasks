package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MimeEmailService {

    @Autowired
    private MailCreatorService mailCreatorService;
    private final JavaMailSender javaMailSender;

    public void sendTrello(final Mail mail) {
        log.info("Preparing to send email");
        try {
            javaMailSender.send(createTrelloMimeMessage(mail));
            log.info("Email sent");
        } catch (MailException e) {
            log.error("Failed to send email: " + e.getMessage(), e);
        }
    }

    public void sendInfo(final Mail mail) {
        log.info("Preparing to send email");
        try {
            javaMailSender.send(createInfoMimeMessage(mail));
            log.info("Email sent");
        } catch (MailException e) {
            log.error("Failed to send email: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createTrelloMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mail.getMailFrom());
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    private MimeMessagePreparator createInfoMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mail.getMailFrom());
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildInfoEmail(mail.getMessage()), true);
        };
    }
}