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

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(BudgetSummaryController.class)
@AutoConfigureMockMvc
public class BudgetSummaryControllerTest {

    @Autowired
    MockMvc mvc;

    // @Autowired


    // @Autowired
    // TransactionRepository tRepo;
    // BudgetCategoryRepository bcRepo;

    @Test
    @Transactional
    @Rollback
    public void testOverall() throws Exception {
        MockHttpServletRequestBuilder request = get("/summary/overall")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionalSum", instanceOf(Number.class) ))
                .andExpect(jsonPath("$.budgetedSum", instanceOf(Number.class) ));
    }




}