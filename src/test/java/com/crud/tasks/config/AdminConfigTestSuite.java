package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdminConfigTestSuite {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void testFromEmailNotNull() {
        //Given & When & Then
        assertNotNull(adminConfig.getFromEmail());
    }

    @Test
    void testAdminEmailNotNull() {
        //Given & When & Then
        assertNotNull(adminConfig.getAdminEmail());
    }
}