package com.epam.learn.javaadvanced.controller;

import com.epam.learn.javaadvanced.model.InfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.application.name}")
    private String expectedAppName;

    @Test
    void getRandomStat_whenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getRandomStat_whenAuthorized() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/info")
                        .with(user("user@example.com").password("password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        InfoResponse response = objectMapper.readValue(responseContent, InfoResponse.class);

        assertEquals(expectedAppName, response.getAppName());
        assertTrue(response.getActiveUsers() >= 0 && response.getActiveUsers() <= 100);
        assertTrue(response.getServerLoad() >= 0 && response.getServerLoad() <= 100);
        assertTrue(response.getRequestsProcessed() >= 0);
        assertTrue(response.getMemoryUsage() >= 0 && response.getMemoryUsage() <= 100);
        assertTrue(response.getCpuUsage() >= 0 && response.getCpuUsage() <= 100);
    }
}
