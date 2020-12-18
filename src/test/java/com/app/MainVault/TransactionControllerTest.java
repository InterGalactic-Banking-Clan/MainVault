package com.app.MainVault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

//    @MockBean
//    private Transaction transactionMocked;
    @Autowired
    MockMvc mvc;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private Transaction transaction;
////    @Autowired
//    private User user;
//    @Autowired
//    private BudgetCategory budgetCategory;

    @Test
    @Transactional
    @Rollback
    public void testGetTransactionValue() throws Exception{
        User user = new User();
        Transaction transaction = new Transaction();

        user.setUsername("username");
        userRepository.save(user);
        transaction.setValue(1);
        transaction.setUser(user);
        transactionRepository.save(transaction);

        MockHttpServletRequestBuilder request = get("/transactions")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value", equalTo(1)));
    }

    @Test
    @Transactional
    @Rollback
    public void testPostTransaction() throws Exception{

        MockHttpServletRequestBuilder request = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": 5," +
                        "    \"budgetCategoryId\": 4," +
                        "    \"dateCreated\": \"2015-05-12\"," +
                        "    \"dateTransaction\": \"2015-05-11\"," +
                        "    \"memo\": \"Transaction\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", equalTo(5)))
                .andExpect(jsonPath("$.budgetCategoryId", equalTo(4)))
                .andExpect(jsonPath("$.dateCreated", equalTo("2015-05-12")))
                .andExpect(jsonPath("$.dateTransaction", equalTo(("2015-05-11"))))
                .andExpect(jsonPath("$.memo", equalTo("Transaction")));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteTransaction() throws Exception{
        User user = new User();
        Transaction transaction = new Transaction();

        user.setUsername("username");
        userRepository.save(user);
        transaction.setValue(1);
        transaction.setUser(user);
        Transaction result = transactionRepository.save(transaction);
        int id = result.getId();
        String url = "/transactions/" + id;

        MockHttpServletRequestBuilder request = delete(url)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder request2 = get(url)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request2)
                .andExpect(status().isOk());
    }
}
