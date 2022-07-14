package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${from.email}")
    private String fromEmail;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.company.name}")
    private String companyName;
}