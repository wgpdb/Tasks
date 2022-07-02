package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testTrelloApiEndpointNotNull() {
        //Given & When & Then
        assertNotNull(trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    void testTrelloAppUsernameNotNull() {
        //Given & When & Then
        assertNotNull(trelloConfig.getTrelloAppUsername());
    }

    @Test
    void testTrelloAppKeyNotNull() {
        //Given & When & Then
        assertNotNull(trelloConfig.getTrelloAppKey());
    }

    @Test
    void testTrelloTokenNotNull() {
        //Given & When & Then
        assertNotNull(trelloConfig.getTrelloToken());
    }
}