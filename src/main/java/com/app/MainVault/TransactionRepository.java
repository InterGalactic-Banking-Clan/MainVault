package com.app.MainVault;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionRepository extends
        CrudRepository<Transaction, Integer> {

     // IMPLEMENT DATE CHECK LATER
//    @Query("SELECT SUM(value) FROM transactions WHERE user_id = ?1")
//    Integer getTransactionSum(int user_id);

//    @Query("SELECT SUM(value) "+
//            "FROM transactions "+
//            "WHERE user_id = ?1 "+
//            "AND budget_category_id = "+
//            "SELECT id FROM budget_categories WHERE user_id = ?1 AND name = ?2")
//    Integer getTransactionSum(int user_id, String category);

//    @Query("SELECT * "+
//            "FROM transactions "+
//            "WHERE user_id = ?1 "+
//            "AND budget_category_id = "+
//            "SELECT id FROM budget_categories WHERE user_id = ?1 AND name = ?2")
//    List<Transaction> getTransactions(int user_id, String category);
}
