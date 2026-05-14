package com.rewards.customer.controller;

import com.rewards.customer.RewardsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RewardsApplication.class)
@AutoConfigureMockMvc
public class RewardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRewardsResponse() throws Exception {

        mockMvc.perform(get("/api/rewards/getAllCustomerRewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].customerId").exists())
                .andExpect(jsonPath("$[0].customerName").exists())
                .andExpect(jsonPath("$[0].monthlyRewards").isArray())
                .andExpect(jsonPath("$[0].totalPoints").exists());
    }
}
