package com.app.MainVault;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/summary")
public class BudgetSummaryController {

    private final TransactionRepository tRepo;
    private final BudgetCategoryRepository bcRepo;
    private final UserRepository uRepo;

    public BudgetSummaryController(
            TransactionRepository tRepo,
            BudgetCategoryRepository bcRepo,
            UserRepository uRepo) {
        this.tRepo = tRepo;
        this.bcRepo = bcRepo;
        this.uRepo = uRepo;
    }

    @GetMapping("/overall")
    public Map<String, Integer> getOverall(
            @RequestParam int user_id){
        Map<String,Integer> ret = new HashMap<>();
        //User user = uRepo.findById(user_id).get();
        Integer tSum = sumTransactions(listTransactions(user_id));
        Integer bcSum = sumBudgetCategories(listBudgetCategories(user_id));
        ret.put("transactionSum", tSum);
        ret.put("budgetSum", bcSum);
        return ret;
    }



//    @GetMapping("/{category}")
//    public Map<String, Integer> getCategorySummary(
//            @RequestParam int user_id,
//            @PathVariable String category){
//        Map<String,Integer> ret = new HashMap<>();
//        Integer tSum = sumTransactions(listTransactions(user_id, category));
//        Integer bcValue = getBudgetCategory(user_id, category).getMonthlyAllocation();
//        ret.put("transactionSum", tSum);
//        ret.put("budgetAllocation", bcValue);
//        return ret;
//    }

//    @GetMapping("/${category}/transactions")
//    public List<Transaction> getCategoryTransactions(
//            @RequestParam int user_id,
//            @PathVariable String category){
//        List<Transaction> ret = tRepo.getTransactions(user_id, category);
//        return ret;
//    }

    private List<Transaction> listTransactions(int user_id){
        Iterator<Transaction> iT = tRepo.findAll().iterator();
        List<Transaction> list = new ArrayList<>();
        while(iT.hasNext()){
            Transaction t = iT.next();
            if(t.getUser().getId() == user_id){
                list.add(t);
            }
        }
        return list;
    }

    private List<Transaction> listTransactions(int user_id, String category){
        List<Transaction> list = listTransactions(user_id);
        BudgetCategory bc = getBudgetCategory(user_id, category);
        for(Transaction t: list){
            if(t.getBudgetCategoryId() == bc.getId()){
                list.add(t);
            }
        }
        return list;
    }

    private Integer sumTransactions(List<Transaction> list){
        int sum = 0;
        for(Transaction t: list){
            sum += t.getValue();
        }
        return sum;
    }

    private List<BudgetCategory> listBudgetCategories(int user_id) {
        Iterator<BudgetCategory> iBC = bcRepo.findAll().iterator();
        List<BudgetCategory> list = new ArrayList<>();
        while(iBC.hasNext()){
            BudgetCategory bc = iBC.next();
            if(bc.getUser().getId() == user_id){
                list.add(bc);
            }
        }
        return list;
    }
    private Integer sumBudgetCategories(List<BudgetCategory> list) {
        int sum = 0;
        for(BudgetCategory bc: list){
            sum += bc.getMonthlyAllocation();
        }
        return sum;
    }

    private BudgetCategory getBudgetCategory(int user_id, String category) {
        List<BudgetCategory> list = listBudgetCategories(user_id);
        System.out.println(list.toString());
        for(BudgetCategory bc: list){
            if(bc.getName() instanceof String){
                if(bc.getName().equals(category)){
                    return bc;
                }
            }

        }
        return new BudgetCategory();
    }



}
