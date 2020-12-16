package com.app.MainVault;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/summary")
public class BudgetSummaryController {

    private final TransactionRepository tRepo;
    private final BudgetCategoryRepository bcRepo;

    public BudgetSummaryController(
            TransactionRepository tRepo,
            BudgetCategoryRepository bcRepo) {
        this.tRepo = tRepo;
        this.bcRepo = bcRepo;
    }

    @GetMapping("/overall")
    public Map<String, Integer> getOverall(){
        int tSum = 0;
        int bcSum = 0;
        Map<String,Integer> ret = new HashMap<>();
        //ret.put("transactionSum", tRepo.findAll()));
        //ret.put("budgetSum", bcRepo.findAll()));
        return ret;
    }


}
