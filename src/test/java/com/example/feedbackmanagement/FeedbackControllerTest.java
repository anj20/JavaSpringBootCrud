package com.example.feedbackmanagement;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateFeedback() throws Exception {
        String feedbackJson = "{\"customerName\":\"John Doe\",\"email\":\"john.doe@example.com\",\"message\":\"Great product!\",\"rating\":5,\"productId\":1,\"userId\":1}";

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(feedbackJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    public void testGetAllFeedback() throws Exception {
        mockMvc.perform(get("/api/feedback"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetFeedbackById() throws Exception {
        mockMvc.perform(get("/api/feedback/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        String feedbackJson = "{\"customerName\":\"John Doe\",\"email\":\"john.doe@example.com\",\"message\":\"Updated feedback message\",\"rating\":4,\"productId\":1,\"userId\":1}";

        mockMvc.perform(put("/api/feedback/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(feedbackJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Updated feedback message"));
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        mockMvc.perform(delete("/api/feedback/1"))
                .andExpect(status().isOk());
    }
}
