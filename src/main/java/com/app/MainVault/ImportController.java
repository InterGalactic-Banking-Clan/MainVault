package com.app.MainVault;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportController {
    @PostMapping("/import")
    public void importTransactionsFile(@RequestBody String json) {

    }
}
