package org.xyz.CRUD_expense_service.controller;

import org.springframework.web.bind.annotation.*;
import org.xyz.CRUD_expense_service.dto.ExpenseRequestDto;
import org.xyz.CRUD_expense_service.dto.ExpenseResponseDto;
import org.xyz.CRUD_expense_service.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ExpenseResponseDto create(@RequestBody ExpenseRequestDto dto) {
        return service.addExpense(dto);
    }

    @GetMapping("/{id}")
    public ExpenseResponseDto getOne(@PathVariable Long id) {
        return service.getExpense(id);
    }

    @GetMapping("/user/{userId}")
    public List<ExpenseResponseDto> getByUser(@PathVariable Long userId) {
        return service.getExpensesByUser(userId);
    }

    @PutMapping("/{id}")
    public ExpenseResponseDto update(@PathVariable Long id, @RequestBody ExpenseRequestDto dto) {
        return service.updateExpense(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteExpense(id);
    }
}
