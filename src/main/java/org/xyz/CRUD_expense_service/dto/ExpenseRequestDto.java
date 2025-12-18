package org.xyz.CRUD_expense_service.dto;

public record ExpenseRequestDto(
        Long userId,
        String title,
        String description,
        Double amount
) {}