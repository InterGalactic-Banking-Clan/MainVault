package com.app.MainVault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ImportControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionRepository repository;

    @Test
    @Transactional
    public void canImportFile() throws Exception {
        String json = getJSON("/exampleTransaction.json");

        MockHttpServletRequestBuilder request = post("/import")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
