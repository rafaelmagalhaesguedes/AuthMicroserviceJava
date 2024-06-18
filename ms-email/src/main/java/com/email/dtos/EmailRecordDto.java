package com.email.dtos;

import java.util.UUID;

/**
 * The type Email record dto.
 */
public record EmailRecordDto(
    UUID userId,
    String emailTo,
    String subject,
    String text
) { }
