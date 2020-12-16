package com.app.MainVault;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportController {
    private final TransactionRepository repository;

    public ImportController(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/import")
    public void importTransactionsFile(@RequestBody String json) {

    }
}
