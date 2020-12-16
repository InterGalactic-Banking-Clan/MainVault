package com.app.MainVault;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetCategoryController {
    @GetMapping("/income")
    public String listOfIncomeSources() {
        return "This GET request is intended to allow the user to list out all income sources";
    }

    @GetMapping("/budget")
    public String listOfBudgetCategories() {
        return "This GET request is intended to allow the user to customize their budget categories";
    }

}
