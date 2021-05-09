package com.cryptodrop.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cryptodrop.domain.Trigger;
import com.cryptodrop.repository.TriggerRepository;

@ExtendWith(MockitoExtension.class)
class TriggerServiceTest {

	@Mock
	TriggerRepository mockTriggerRepo;

	@InjectMocks
	TriggerService triggerService;

	@Test
	void testCheckTriggers() {
		when(mockTriggerRepo.findAll())
				.thenReturn(Arrays.asList(Trigger.builder().id(1).coin("bitcoin").frequency(5).rateChange(1.0).build(),
						Trigger.builder().id(2).coin("bitcoin").frequency(10).rateChange(2.0).build(),
						Trigger.builder().id(3).coin("bitcoin").frequency(15).rateChange(3.0).build(),
						Trigger.builder().id(4).coin("dogecoin").frequency(5).rateChange(5.0).build(),
						Trigger.builder().id(5).coin("dogecoin").frequency(10).rateChange(10.0).build(),
						Trigger.builder().id(6).coin("dogecoin").frequency(15).rateChange(15.0).build()));

		Map<String, Double[]> pctChange = new HashMap<>();
		pctChange.put("bitcoin", new Double[] { 1.0, 1.5, 4.0 });
		pctChange.put("dogecoin", new Double[] { 2.0, 7.0, 20.0 });
		pctChange.put("litecoin", new Double[] { 20.0, 30.0, 30.0 });
		
		triggerService.checkTriggers(pctChange);

	}

}
