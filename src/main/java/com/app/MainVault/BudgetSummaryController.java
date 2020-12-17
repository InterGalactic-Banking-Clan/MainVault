package com.app.MainVault;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Integer> getOverall(
            @RequestParam int user_id){
        Map<String,Integer> ret = new HashMap<>();
//        ret.put("transactionSum", tRepo.getTransactionSum(user_id));
//        ret.put("budgetSum", bcRepo.getAllocationSum(user_id));
        return ret;
    }

//    @GetMapping("/${category}")
//    public Map<String, Integer> getCategorySummary(
//            @RequestParam int user_id,
//            @PathVariable String category){
//        Map<String,Integer> ret = new HashMap<>();
//        ret.put("transactionSum", tRepo.getTransactionSum(user_id, category));
//        ret.put("budgetSum", bcRepo.getAllocation(user_id, category));
//        return ret;
//    }

//    @GetMapping("/${category}/transactions")
//    public List<Transaction> getCategoryTransactions(
//            @RequestParam int user_id,
//            @PathVariable String category){
//        List<Transaction> ret = tRepo.getTransactions(user_id, category);
//        return ret;
//    }


}
