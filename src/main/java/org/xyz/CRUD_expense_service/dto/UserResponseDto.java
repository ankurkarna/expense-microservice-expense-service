package org.xyz.CRUD_expense_service.dto;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String address,
        Integer age,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
