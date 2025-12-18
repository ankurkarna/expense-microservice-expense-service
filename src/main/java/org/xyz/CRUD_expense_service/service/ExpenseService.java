package org.xyz.CRUD_expense_service.service;

import org.springframework.stereotype.Service;
import org.xyz.CRUD_expense_service.Mapper.ExpenseMapper;
import org.xyz.CRUD_expense_service.client.UserClient;
import org.xyz.CRUD_expense_service.dto.ExpenseRequestDto;
import org.xyz.CRUD_expense_service.dto.ExpenseResponseDto;
import org.xyz.CRUD_expense_service.dto.UserResponseDto;
import org.xyz.CRUD_expense_service.entity.Expense;
import org.xyz.CRUD_expense_service.exception.ExpenseNotFoundException;
import org.xyz.CRUD_expense_service.exception.UserNotFoundException;
import org.xyz.CRUD_expense_service.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {

    private final UserClient userClient;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(UserClient userClient, ExpenseRepository expenseRepository) {
        this.userClient = userClient;
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto) {

        UserResponseDto user = userClient.getUserById(expenseRequestDto.userId());
//        if (user.id() == null) throw new UserNotFoundException("User not found with id: " + expenseRequestDto.userId());

        Expense expense = new Expense();
        expense.setUserId(user.id());
        expense.setTitle(expenseRequestDto.title());
        expense.setDescription(expenseRequestDto.description());
        expense.setAmount(expenseRequestDto.amount());

        return ExpenseMapper.toDto(expenseRepository.save(expense));
    }

    public ExpenseResponseDto getExpense(Long id) {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        return ExpenseMapper.toDto(e);
    }

    public List<ExpenseResponseDto> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    public ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));

        expense.setUserId(expense.getUserId());

        if (dto.title() != null) {
            expense.setTitle(dto.title());
        }
        if (dto.description() != null) {
            expense.setDescription(dto.description());
        }
        if (dto.amount() != null) {
            expense.setAmount(dto.amount());
        }

        return ExpenseMapper.toDto(expenseRepository.save(expense));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
