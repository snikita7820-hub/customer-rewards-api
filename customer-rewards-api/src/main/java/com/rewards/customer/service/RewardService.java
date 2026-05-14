package com.rewards.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rewards.customer.dto.CustomerRewardSummary;
import com.rewards.customer.dto.MonthlyReward;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.exception.InvalidTransactionException;
import com.rewards.customer.repository.TransactionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    public static TransactionRepository transactionRepository;

    private final List<Transaction> transactions;

    public RewardService(TransactionRepository transactionRepository, List<Transaction> transactions) {
        this.transactionRepository = transactionRepository;
        this.transactions = transactions;
    }


    public RewardService() {

        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            InputStream inputStream = new ClassPathResource("transactions.json").getInputStream();

            transactions = mapper.readValue(inputStream, new TypeReference<List<Transaction>>() {
            });

        } catch (Exception e) {

            throw new RuntimeException("Failed to load", e);
        }
    }

    public List<CustomerRewardSummary> getAllCustomerRewards() {

        return calculateRewards(transactions);
    }

    public CustomerRewardSummary getCustomerRewards(Long customerId) {

        List<Transaction> customerTransactions = transactions.stream().filter(t -> t.getCustomerId().equals(customerId)).collect(Collectors.toList());

        return customerRewardSummary(customerId, customerTransactions);
    }

    private CustomerRewardSummary customerRewardSummary(Long customerId, List<Transaction> transactions) {

        Map<Month, Integer> monthlyPntsMap = new HashMap<>();

        int totalPoints = 0;

        for (Transaction transaction : transactions) {

            int points = calculatePnts(transaction.getAmount(), transaction.getCustomerName());

            Month month = transaction.getTransactionDate().getMonth();

            monthlyPntsMap.put(month, monthlyPntsMap.getOrDefault(month, 0) + points);

            totalPoints += points;
        }

        List<MonthlyReward> monthlyRewards = new ArrayList<>();

        for (Map.Entry<Month, Integer> entry : monthlyPntsMap.entrySet()) {

            MonthlyReward reward = new MonthlyReward();

            reward.setMonth(entry.getKey().toString());
            reward.setPoints(entry.getValue());

            monthlyRewards.add(reward);
        }

        CustomerRewardSummary summary = new CustomerRewardSummary();

        summary.setCustomerId(customerId);

        if (!transactions.isEmpty()) {
            summary.setCustomerName(transactions.get(0).getCustomerName());
        }

        summary.setMonthlyRewards(monthlyRewards);
        summary.setTotalPoints(totalPoints);

        return summary;
    }

    public List<CustomerRewardSummary> calculateRewards(List<Transaction> transactions) {

        Map<Long, List<Transaction>> customerTransactions = transactions.stream().collect(Collectors.groupingBy(Transaction::getCustomerId));

        List<CustomerRewardSummary> response = new ArrayList<>();

        for (Map.Entry<Long, List<Transaction>> entry : customerTransactions.entrySet()) {

            Long customerId = entry.getKey();

            List<Transaction> customerTransactionsList = entry.getValue();

            Map<String, Integer> monthlyRewards = new HashMap<>();

            int totalRewards = 0;

            for (Transaction transaction : customerTransactionsList) {

                int points = calculatePnts(transaction.getAmount(), transaction.getCustomerName());

                String month = transaction.getTransactionDate().getMonth().toString();

                monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0) + points);

                totalRewards += points;
            }

            List<MonthlyReward> monthlyRewardList = monthlyRewards.entrySet().stream().map(entrySet -> new MonthlyReward(entrySet.getKey(), entrySet.getValue())).toList();

            response.add(new CustomerRewardSummary(customerId, customerTransactionsList.get(0).getCustomerName(), monthlyRewardList, totalRewards));
        }

        return response;
    }

    public int calculatePnts(double amount, String custName) {

        if (amount < 0) {
            throw new InvalidTransactionException("Transaction amount can't be negative for customer " + custName);
        } else if (amount <= 50) {
            return 0;
        } else if (amount <= 100) {
            return (int) (amount - 50);
        }

        return (int) ((amount - 100) * 2 + 50);
    }
}