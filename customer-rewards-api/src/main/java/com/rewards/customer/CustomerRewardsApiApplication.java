package com.rewards.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CustomerRewardsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerRewardsApiApplication.class, args);
    }

}
