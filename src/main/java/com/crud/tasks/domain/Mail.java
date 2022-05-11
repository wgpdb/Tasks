package com.crud.tasks.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class Mail {

    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;
}