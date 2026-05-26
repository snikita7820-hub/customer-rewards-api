package com.rewards.customer.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.customer.dto.Reward;
import com.rewards.customer.entity.Transaction;
import com.rewards.customer.service.RewardsService;

import jakarta.validation.Valid;


/**
 * REST Controller responsible for handling reward point related APIs.
 *
 * This controller exposes endpoints to calculate reward points
 * for customers based on their transaction history.
 *
 *
 *
 */
@Validated
@RestController
@RequestMapping("/api/rewards")
@Tag(name = "Rewards Controller", description = "Reward Points APIs")
public class RewardsController {

    @Autowired
    RewardsService service;


    /**
     * Calculates reward points for all customer transactions.
     *
     * This API:
     * Fetches all customer transactions
     * Calculates monthly reward points
     * Calculates total reward points
     * Returns reward summary for each customer
     *
     * Endpoint:
     * GET /rewards/calculate-reward-points
     *
     * @return List of customer rewards containing:
     *         customer id,
     *         monthly reward points,
     *         total reward points
     */
    @GetMapping("/calculate-reward-points")
    @Operation(summary = "Get customer reward points")
    public List<Reward> getRewardPoints() {
        List<Transaction> transList = service.getTransactionList();
        List<Reward> rewardList = service.getrewardpoints(transList);

        return rewardList;
    }


    /*
     * calculate reward points for given transactions
     * @param list of transactions
     * @return reward points per customer
     */
    @PostMapping("/calculate-reward-points")
    @Operation(summary = "Get customer reward points using transaction list")
    public List<Reward> getRewardPoints(@RequestBody @Valid List<Transaction> transList) {

        if(transList == null || transList.isEmpty()) {
            throw new IllegalArgumentException("Transaction list cannot be empty");
        }
        List<Reward> rewardList = service.getrewardpoints(transList);

        return rewardList;
    }

}
