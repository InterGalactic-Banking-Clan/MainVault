package com.app.MainVault;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImportController {
    private final TransactionRepository repository;

    public ImportController(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/import")
    public List<Transaction> handleFileUpload(@RequestBody List<Transaction> file) {
        User user = new User();
        user.setId(1);
        user.setUsername("Obi-Wan Kenobi");
        user.setPassword("highGround");
        file.forEach(transaction -> {
            transaction.setUser(user);
            this.repository.save(transaction);
        });
        return file;
    }
}
