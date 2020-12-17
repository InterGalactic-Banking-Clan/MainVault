package com.app.MainVault;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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
    public String listOfBudgetCategories() {
        //return "This GET request is intended to allow the user to customize their budget categories";
        return String.valueOf(budgetCategoryRepository.findAll());
    } //Intent to list out all categories

    @GetMapping("/budget/{id}")
    ResponseEntity<?> getBudgetCategory(@PathVariable int id) {
        //return "This POST request allows the user to customize their budget categories";
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(id);
        return budgetCategory.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } //Using ResponseEntity to configure the HTTP response

    @PostMapping("/budget/categories")
    ResponseEntity<BudgetCategory> creatingBudgetCategory(@RequestBody BudgetCategory budgetCategory) throws Exception {
        //return "This PATCH request allows the user to modify/edit their custom categories";
        BudgetCategory result = (BudgetCategory) budgetCategoryRepository.save(budgetCategory);
        return ResponseEntity.created(new URI("/budget/category" + result.getId())).body(result);
    } //I'm hoping that this will create a budget cat

    @PutMapping("/budget/{id}")
    ResponseEntity<BudgetCategory> updatingBudgetCategory(@RequestBody BudgetCategory budgetCategory) {
        BudgetCategory result = (BudgetCategory) budgetCategoryRepository.save(budgetCategory);
        return ResponseEntity.ok().body(result);
    } //I'm hoping that this will update a budget cat

    @DeleteMapping("/budget/{id}")
    ResponseEntity<?> deletingBudgetCategory(@PathVariable int id) {
        budgetCategoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } //I'm hoping that this will delete a budget cat

}
