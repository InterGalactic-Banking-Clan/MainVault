package com.app.MainVault;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repository;
    private final UserRepository userRepository;
    private final User user;

    public TransactionController(TransactionRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.user = new User();
        this.user.setUsername("username1");
        this.user.setPassword("password1");
        this.userRepository.save(user);
    }

    @GetMapping("")
    public Iterable<Transaction> getTransactionList(){
        return this.repository.findAll();
    }

    @PostMapping("")
    public Transaction postTransaction(@RequestBody Transaction transaction){
        transaction.setUser(user);
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
