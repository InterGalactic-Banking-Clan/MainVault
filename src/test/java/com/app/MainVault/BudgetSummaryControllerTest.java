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

    @BeforeEach
    public void setup() throws Exception{
        String json = getJSON("/exampleTransaction.json");

        MockHttpServletRequestBuilder request = post("/import")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request);
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
                .andExpect(jsonPath("$.transactionalSum", instanceOf(Number.class) ))
                .andExpect(jsonPath("$.budgetedSum", instanceOf(Number.class) ));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }


}