package com.app.MainVault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BudgetCategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    //Lines 23-24 is pulling the sampling data to run test against
    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testingBudgetStatus() throws Exception {
        this.mvc.perform(
                get("/budget")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
    }//Checks the status to the server

    @Test
    @Transactional
    public void gettingBudgetCatById() throws Exception {
        BudgetCategory budgetCategory = new BudgetCategory();
        User user = new User();
        user.setId(1);
        budgetCategory.setUser(user);
        BudgetCategory result = budgetCategoryRepository.save(budgetCategory);
        String url = "/budget/" + result.getId();

        MockHttpServletRequestBuilder request = get(url)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(result.getId())));
    }//Testing the ability to get a budget category by it's id

    @Test
    @Transactional
    public void creatingNewBudgetCategory() throws Exception {

        MockHttpServletRequestBuilder request = post("/budget/categories/Car Loan")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Car Loan")));

    }//Testing the ability to create a budget category; getting status errors - Expected status 201, Actual status 405

    @Test
    @Transactional
    public void updatingBudgetCategory() throws Exception {

        MockHttpServletRequestBuilder request = put("/budget/2")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.monthlyAllocation", is(100)));
    }//Testing the ability to modify/edit a budget category; status errors - expected status 202, actual status 400

    @Test
    @Transactional
    public void deletingBudgetCategory() throws Exception {
        BudgetCategory budgetCategory = new BudgetCategory();
        User user = new User();
        user.setId(6);
        budgetCategory.setUser(user);
        BudgetCategory result = budgetCategoryRepository.save(budgetCategory);
        String url = "/budget/" + result.getId();

        MockHttpServletRequestBuilder request = delete(url)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder request2 = get(url)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request2)
                .andExpect(status().isNotFound());
    }//Testing the ability to delete a budget category; request processing failed

}
