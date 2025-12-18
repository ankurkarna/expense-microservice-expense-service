package org.xyz.CRUD_expense_service.dto;

import java.time.LocalDateTime;

public record ExpenseResponseDto(
        Long id,
        Long userId,
        String title,
        String description,
        Double amount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}