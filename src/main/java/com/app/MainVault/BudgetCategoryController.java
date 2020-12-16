package com.app.MainVault;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetCategoryController {
//    @GetMapping("/income")
//    public String listOfIncomeSources() {
//        return "This GET request is intended to allow the user to list out all income sources";
//    } Commented out in case we need later

    @GetMapping("/budget")
    public String listOfBudgetCategories() {
        return "This GET request is intended to allow the user to customize their budget categories";
    }

    @PostMapping("/budget/categories")
    public String customizingCategories() {
        return "This POST request allows the user to customize their budget categories";
    }

    @PatchMapping("/budget/categories")
    public String modifyingCustomCategories() {
        return "This PATCH request allows the user to modify/edit their custom categories";
    }

}
