package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private final String dayOfWeek = LocalDate.now().getDayOfWeek().name().toLowerCase();

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://wgpdb.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_message", message.substring(message.indexOf("\""), message.indexOf("\"")));
        context.setVariable("goodbye_message", "Have a great " + dayOfWeek + "! :-)");
        context.setVariable("company_name", adminConfig.getCompanyName());

        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}