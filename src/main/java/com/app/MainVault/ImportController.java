package com.app.MainVault;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImportController {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final User defaultUser;

    public ImportController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.defaultUser = new User();
        this.defaultUser.setUsername("Obi-Wan Kenobi");
        this.defaultUser.setPassword("highGround");
        this.userRepository.save(this.defaultUser);
    }

    @PostMapping("/import")
    public List<Transaction> handleFileUpload(@RequestBody List<Transaction> file) {
        file.forEach(transaction -> {
            transaction.setUser(this.defaultUser);
            this.transactionRepository.save(transaction);
        });
        return file;
    }
}
