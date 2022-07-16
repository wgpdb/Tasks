package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MimeEmailService;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final MimeEmailService mimeEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: morning report";

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 600000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String noun = size == 1 ? " task." : " tasks.";
        mimeEmailService.sendInfo(
                new Mail(
                        adminConfig.getFromEmail(),
                        adminConfig.getAdminEmail(),
                        null,
                        SUBJECT,
                        "You have " + size + noun
                )
        );
    }
}