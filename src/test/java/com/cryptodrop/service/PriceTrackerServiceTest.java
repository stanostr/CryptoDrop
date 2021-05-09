package com.cryptodrop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cryptodrop.client.CoinDataClient;

@ExtendWith(MockitoExtension.class)
class PriceTrackerServiceTest {
	
	@Mock
	TriggerService mockTriggerService;
	
	@Mock
	CoinDataClient mockClient;
	
	@InjectMocks
	PriceTrackerService priceTrackerService;
	
	void testGetData()
	{
		
	}

}
