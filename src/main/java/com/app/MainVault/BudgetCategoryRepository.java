package com.app.MainVault;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BudgetCategoryRepository extends
        CrudRepository<BudgetCategory, Integer> {

//    @Query("SELECT SUM(monthly_allocation) FROM budget_categories WHERE user_id = ?1 AND active = 1")
//    Integer getAllocationSum(int user_id);

//    @Query("SELECT monthly_allocation FROM budget_categories WHERE user_id = ?1 AND active = 1 AND budget_category = ?2")
//    Integer getAllocation(int user_id, String budget_category);
}
