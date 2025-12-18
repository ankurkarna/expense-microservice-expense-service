package org.xyz.CRUD_expense_service.Mapper;


import org.xyz.CRUD_expense_service.dto.ExpenseRequestDto;
import org.xyz.CRUD_expense_service.dto.ExpenseResponseDto;
import org.xyz.CRUD_expense_service.entity.Expense;

import java.time.LocalDateTime;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseRequestDto dto) {
        Expense expense = new Expense();
        expense.setUserId(dto.userId());
        expense.setTitle(dto.title());
        expense.setDescription(dto.description());
        expense.setAmount(dto.amount());
        return expense;
    }

    public static ExpenseResponseDto toDto(Expense expense) {
        return new ExpenseResponseDto(
                expense.getId(),
                expense.getUserId(),
                expense.getTitle(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }
}
