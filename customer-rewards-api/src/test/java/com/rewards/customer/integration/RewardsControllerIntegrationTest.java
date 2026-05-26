package com.rewards.customer.integration;

import com.rewards.customer.dto.Reward;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.exception.InvalidTransactionException;
import com.rewards.customer.repository.TransactionRepository;
import com.rewards.customer.service.RewardsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RewardsServiceImplTest {

    @Mock
    private TransactionRepository repo;

    @InjectMocks
    private RewardsServiceImpl service;

    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    void setUp() {

        transaction1 = new Transaction();
        transaction1.setTxnId(1);
        transaction1.setCustId("CUSTOMER1");
        transaction1.setAmount(BigDecimal.valueOf(120.0));
        transaction1.setDate(LocalDate.of(2025, 1, 10));

        transaction2 = new Transaction();
        transaction2.setTxnId(2);
        transaction2.setCustId("CUSTOMER1");
        transaction2.setAmount(BigDecimal.valueOf(80.0));
        transaction2.setDate(LocalDate.of(2025, 1, 15));
    }

    @Test
    void testGetRewardPoints() {

        List<Transaction> transactions =
                Arrays.asList(transaction1, transaction2);

        List<Reward> rewards = service.getrewardpoints(transactions);

        assertNotNull(rewards);
        assertEquals(1, rewards.size());

        Reward reward = rewards.get(0);

        assertEquals("CUSTOMER1", reward.getCustId());


        assertEquals(120, reward.getTotalRewardPoints());

        assertTrue(reward.getMonthlyRewardPoints()
                .containsKey("JANUARY"));

        assertEquals(120,
                reward.getMonthlyRewardPoints().get("JANUARY"));
    }

    @Test
    void testNegativeTransactionAmount() {

        Transaction transaction = new Transaction();
        transaction.setCustId("CUSTOMER1");
        transaction.setAmount(BigDecimal.valueOf(-100.0));
        transaction.setDate(LocalDate.now());

        List<Transaction> list = List.of(transaction);

        assertThrows(InvalidTransactionException.class,
                () -> service.getrewardpoints(list));
    }

    @Test
    void testGetTransactionList() {

        List<Transaction> transactions =
                Arrays.asList(transaction1, transaction2);

        when(repo.findAll()).thenReturn(transactions);

        List<Transaction> result = service.getTransactionList();

        assertEquals(2, result.size());

        verify(repo, times(1)).findAll();
    }
}