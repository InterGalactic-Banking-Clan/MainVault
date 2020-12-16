package com.app.MainVault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import static org.hamcrest.Matchers.instanceOf;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private TransactionRepository repository;
//    @Autowired
//    private Transaction transaction;
////    @Autowired
//    private User user;
//    @Autowired
//    private BudgetCategory budgetCategory;

    @Test
    @Transactional
    @Rollback
    public void testTransactionList() throws Exception{
//        Transaction transaction = new Transaction();
//        transaction.setValue(1);
//        repository.save(transaction);

        MockHttpServletRequestBuilder request = get("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", instanceOf(Number.class)));
    }

}
