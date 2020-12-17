package com.app.MainVault;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends
        CrudRepository<Transaction, Integer> {

}
