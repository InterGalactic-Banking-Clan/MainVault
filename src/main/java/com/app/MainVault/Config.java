package com.app.MainVault;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public Transaction getTransaction() {
        return new Transaction();
    }

    @Bean
    public BudgetCategory getBudgetCategory() {
        return new BudgetCategory();
    }
}
