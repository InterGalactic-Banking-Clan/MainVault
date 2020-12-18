package com.app.MainVault;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import javax.transaction.Transactional;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(BudgetSummaryController.class)
@AutoConfigureMockMvc
public class BudgetSummaryControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    TransactionRepository tRepo;
    @Autowired
    BudgetCategoryRepository bcRepo;
    @Autowired
    UserRepository uRepo;


    @BeforeEach
    @Transactional
    @Rollback
    public void setup() throws Exception {
        String json = getJSON("/exampleTransaction.json");

        MockHttpServletRequestBuilder request = post("/import")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        this.mvc.perform(request);
        for (int i = 0; i < 10; i++) {
            MockHttpServletRequestBuilder request2 = post("/budget/categories/cat" + i)
                    .contentType(MediaType.APPLICATION_JSON);
            String responseBody = this.mvc.perform(request2).andReturn().getResponse().getContentAsString();
            MockHttpServletRequestBuilder request3 = put("/budget/" + i)
                    .contentType(MediaType.APPLICATION_JSON);
            this.mvc.perform(request3);
        }

    }

    @Test
    @Transactional
    @Rollback
    public void testOverall() throws Exception {
        MockHttpServletRequestBuilder request = get("/summary/overall?user_id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionSum", instanceOf(Number.class)))
                .andExpect(jsonPath("$.budgetSum", instanceOf(Number.class)))
                .andExpect(jsonPath("$.transactionSum",is(3147600)))
                .andExpect(jsonPath("$.budgetSum",is(900)));
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void testCategorySummary() throws Exception {
//        MockHttpServletRequestBuilder request = get("/summary/cat6?user_id=1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"\"}");
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.transactionSum", instanceOf(Number.class)))
//                .andExpect(jsonPath("$.budgetSum", instanceOf(Number.class)))
//                .andExpect(jsonPath("$.transactionSum",is(3147600)))
//                .andExpect(jsonPath("$.budgetSum",is(900)));
//    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }


}