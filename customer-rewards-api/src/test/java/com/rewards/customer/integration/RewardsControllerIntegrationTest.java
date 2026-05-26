package com.rewards.customer.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.rewards.customer.CustomerRewardsApiApplication;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.repository.TransactionRepository;


@AutoConfigureMockMvc
@SpringBootTest(classes = CustomerRewardsApiApplication.class)
class RewardsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository repo;

    @BeforeEach
    void setup() {

        repo.deleteAll();

        Transaction t1 = new Transaction();
        t1.setCustId("CUSTOMER1");
        t1.setAmount(BigDecimal.valueOf(120.0));
        t1.setDate(LocalDate.of(2025, 1, 10));

        Transaction t2 = new Transaction();
        t2.setCustId("CUSTOMER1");
        t2.setAmount(BigDecimal.valueOf(80.0));
        t2.setDate(LocalDate.of(2025, 1, 15));

        repo.save(t1);
        repo.save(t2);
    }

    @Test
    void testCalculateRewardPoints() throws Exception {

        mockMvc.perform(
                        get("/api/rewards/calculate-reward-points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].custId")
                        .value("CUSTOMER1"))
                .andExpect(jsonPath("$[0].totalRewardPoints")
                        .value(120))
                .andExpect(jsonPath(
                        "$[0].monthlyRewardPoints.JANUARY")
                        .value(120));
    }
}