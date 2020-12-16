package com.app.MainVault;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Transaction> getTransactionList(){
        return this.repository.findAll();
    }

    @PostMapping("")
    public Transaction postTransaction(@RequestBody Transaction transaction){
        return this.repository.save(transaction);
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable int id){
        return this.repository.findById(id);
    }

    @PatchMapping("/{id}")
    public Transaction editTransaction(@PathVariable int id, @RequestBody Transaction newTransaction){
        return this.repository.save(newTransaction); //need updates

//        Transaction oldTransaction = this.repository.findById(id);
//        Transaction newLesson = newTransaction.get();
//        newLesson.setTitle(lessonDetails.getTitle());
//        newLesson.setDeliveredOn(lessonDetails.getDeliveredOn());
//        this.repository.save(newLesson);
//
//        return lesson.get();
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable int id){
        this.repository.deleteById(id);
    }

}
