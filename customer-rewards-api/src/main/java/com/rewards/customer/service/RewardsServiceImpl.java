package com.rewards.customer.service;

import com.rewards.customer.dto.Reward;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.repository.TransactionRepository;
import com.rewards.customer.util.RewardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service implementation class responsible for
 * reward point calculation logic.
 */
@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    TransactionRepository repo;

    /**
     * Calculates reward points for the given list of transactions.
     * <p>
     * This method:
     * Calculates reward points for each transaction
     * Groups rewards by customer and month
     * Calculates total reward points per customer
     *
     * @param transList List of customer transactions
     * @return List of reward summaries containing:
     * customer id,
     * monthly reward points,
     * total reward points
     */
    @Override
    public List<Reward> getrewardpoints(List<Transaction> transList) {

        Map<String, Map<String, Integer>> customerMonthlyRewards = new HashMap<>();
        Map<String, Integer> customerTotalRewards = new HashMap<>();

        for (Transaction trans : transList) {

            String custId = trans.getCustId();
            String month = trans.getDate().getMonth().toString();

            int points = RewardUtil.calculateRewardPoints(trans.getAmount());

            customerMonthlyRewards.putIfAbsent(custId, new HashMap<>());
            customerTotalRewards.putIfAbsent(custId, 0);

            Map<String, Integer> monthlyPoints = customerMonthlyRewards.get(custId);

            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);

            customerTotalRewards.put(custId, customerTotalRewards.get(custId) + points);

        }

        List<Reward> rewardlist = new ArrayList<>();

        for (String customerId : customerMonthlyRewards.keySet()) {
            Reward reward = new Reward();
            reward.setCustId(customerId);
            reward.setTotalRewardPoints(customerTotalRewards.get(customerId));
            reward.setMonthlyRewardPoints(customerMonthlyRewards.get(customerId));
            rewardlist.add(reward);

        }

        return rewardlist;
    }


    /**
     * Retrieves all customer transactions from database.
     * <p>
     * return List of customer transactions
     */
    @Override
    public List<Transaction> getTransactionList() {

        return repo.findAll();
    }

}