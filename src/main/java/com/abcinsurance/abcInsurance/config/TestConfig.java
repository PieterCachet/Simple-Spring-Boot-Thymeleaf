package com.abcinsurance.abcInsurance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class TestConfig {

    @Scheduled(cron = "0 */1 * ? * *")
    public void checkClaims() {
        log.debug("Running claim checker");
        System.out.println("Calling Conjob");
    }

}
