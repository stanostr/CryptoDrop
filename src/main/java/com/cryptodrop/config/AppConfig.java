package com.cryptodrop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.cryptodrop.domain.Trigger;
import com.cryptodrop.repository.TriggerRepository;
import com.cryptodrop.service.TriggerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class AppConfig {
	@Bean
	public RestTemplate coinDataRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner triggerLoader(TriggerRepository repo, TriggerService triggerService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				log.info("Pre-loading triggers.");
				repo.save(Trigger.builder().id(1).coin("bitcoin").frequency(5).rateChange(1.0).build());
				repo.save(Trigger.builder().id(2).coin("bitcoin").frequency(10).rateChange(2.0).build());
				repo.save(Trigger.builder().id(3).coin("bitcoin").frequency(15).rateChange(2.0).build());
				repo.save(Trigger.builder().id(4).coin("bitcoin").frequency(15).rateChange(5.0).build());
			}
		};
	}
}
