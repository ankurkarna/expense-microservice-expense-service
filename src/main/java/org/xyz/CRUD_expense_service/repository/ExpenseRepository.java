package org.xyz.CRUD_expense_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.xyz.CRUD_expense_service.entity.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
}
