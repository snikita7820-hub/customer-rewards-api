package com.rewards.customer.controller;

import com.rewards.customer.dto.CustomerRewardSummary;
import com.rewards.customer.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@Tag(name = "Rewards API", description = "Customer rewards APIs")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/getAllCustomerRewards")
    @Operation(summary = "Get rewards for all customers")
    public List<CustomerRewardSummary> getRewards() {
        return rewardService.getAllCustomerRewards();
    }

    @GetMapping("/getRewardByCustomerId/{customerId}")
    @Operation(summary = "Get rewards for customer by customerId")
    public CustomerRewardSummary getCustomerRewards(@PathVariable Long customerId) {
        return rewardService.getCustomerRewards(customerId);

    }


}
