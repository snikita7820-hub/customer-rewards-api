package com.rewards.customer.controller;

import com.rewards.customer.dto.Reward;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.service.RewardsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RewardsControllerTest {

    @Mock
    private RewardsService service;

    @InjectMocks
    private RewardsController controller;

    @Test
    void testGetCreditPoints() {

        Transaction transaction = new Transaction();

        Reward reward = new Reward();
        reward.setCustId("CUSTOMER1");
        reward.setTotalRewardPoints(100);

        when(service.getTransactionList())
                .thenReturn(List.of(transaction));

        when(service.getrewardpoints(anyList()))
                .thenReturn(List.of(reward));

        List<Reward> response = controller.getRewardPoints();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("CUSTOMER1",
                response.get(0).getCustId());

        verify(service, times(1))
                .getTransactionList();

        verify(service, times(1))
                .getrewardpoints(anyList());
    }
}