package com.app.MainVault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RestController
public class BudgetCategoryController {

    private final BudgetCategoryRepository budgetCategoryRepository;

    public BudgetCategoryController(BudgetCategoryRepository budgetCategoryRepository) {
        //super();
        this.budgetCategoryRepository = budgetCategoryRepository;
    }
//    @GetMapping("/income")
//    public String listOfIncomeSources() {
//        return "This GET request is intended to allow the user to list out all income sources";
//    } Commented out in case we need later

    @GetMapping("/budget")
    public Iterable<BudgetCategory> listOfBudgetCategories() {
        //return "This GET request is intended to allow the user to customize their budget categories";
        return budgetCategoryRepository.findAll();
    } //Intent to list out all categories from the budget_categories table

    @GetMapping("/budget/{id}")
    ResponseEntity<?> getBudgetCategory(@PathVariable int id) {
        //return "This POST request allows the user to customize their budget categories";
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(id);
        return budgetCategory.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } //Using ResponseEntity to configure the HTTP response

    @PostMapping("/budget/categories/{name}")
    ResponseEntity<BudgetCategory> creatingBudgetCategory(@PathVariable String name) throws Exception {
        //return "This PATCH request allows the user to modify/edit their custom categories";
        BudgetCategory budgetCategory = new BudgetCategory();
        User user = new User();
        user.setId(1);
        budgetCategory.setUser(user);
        budgetCategory.setName(name);
        BudgetCategory result = (BudgetCategory) budgetCategoryRepository.save(budgetCategory);
        return ResponseEntity.created(new URI("/budget/categories" + result.getId())).body(result);
    } //I'm hoping that this will create a budget category in the budget_categories table

    @PutMapping("/budget/{id}")
    ResponseEntity<BudgetCategory> updatingBudgetCategory(@PathVariable int id) {
        BudgetCategory budgetCategory = new BudgetCategory();
        User user = new User();
        user.setId(1);
        budgetCategory.setUser(user);
        budgetCategory.setMonthlyAllocation(100);
        BudgetCategory result = (BudgetCategory) budgetCategoryRepository.save(budgetCategory);
        return ResponseEntity.accepted().body(result);
    } //I'm hoping that this will update a budget category in the budget_categories table

    @DeleteMapping("/budget/{id}")
    ResponseEntity<?> deletingBudgetCategory(@PathVariable int id) {
        budgetCategoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } //I'm hoping that this will delete a budget category in the budget_categories tables

}
