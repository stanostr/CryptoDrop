package com.cryptodrop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cryptodrop.client.CoinDataClient;
import com.cryptodrop.domain.Coin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PriceTrackerService {
	@Value("#{'${crypto.coins}'.split(',')}")
	private List<String> coins;

	private Map<Integer, Map<String, Double>> priceMaps;

	@Autowired
	private CoinDataClient client;
	
	@Autowired
	private TriggerService triggerService;

	@PostConstruct
	private void init() {
		priceMaps = Map.of(1, new HashMap<String, Double>(coins.size()), 2, new HashMap<String, Double>(coins.size()),
				3, new HashMap<String, Double>(coins.size()));
		log.info("Loading initial coin pricing data.");
		Map<String, Coin> initData = client.getCoinData();
		for (Entry<String, Coin> coin : initData.entrySet()) {
			priceMaps.get(1).put(coin.getKey(), coin.getValue().getUsd());
			priceMaps.get(2).put(coin.getKey(), coin.getValue().getUsd());
			priceMaps.get(3).put(coin.getKey(), coin.getValue().getUsd());
		}
	}

	@Scheduled(fixedDelay = 300000, initialDelay = 300000)
	public void getData() {
		Map<String, Coin> data = client.getCoinData();
		Map<String, Double[]> pctChange = new HashMap<>();
		for (String coin : coins) {
			double newPrice = data.get(coin).getUsd();
			log.info(coin + ": " + newPrice);
			double percentChange = ((newPrice - priceMaps.get(1).get(coin)) / priceMaps.get(1).get(coin)) * 100;
			double percentChange2 = ((newPrice - priceMaps.get(2).get(coin)) / priceMaps.get(2).get(coin)) * 100;
			double percentChange3 = ((newPrice - priceMaps.get(3).get(coin)) / priceMaps.get(3).get(coin)) * 100;
			pctChange.put(coin, new Double[] {percentChange, percentChange2, percentChange3});

			log.info(coin + " 5min: " + priceMaps.get(1).get(coin));
			log.info(coin + " 10min: " + priceMaps.get(2).get(coin));
			log.info(coin + " 15min: " + priceMaps.get(3).get(coin));

			log.info("{} % change 5 minutes: {}", coin, percentChange);
			log.info("{} % change 10 minutes: {}", coin, percentChange2);
			log.info("{} % change 15 minutes: {}", coin, percentChange3);

			priceMaps.get(3).put(coin, priceMaps.get(2).get(coin));
			priceMaps.get(2).put(coin, priceMaps.get(1).get(coin));
			priceMaps.get(1).put(coin, newPrice);
		}
		
		triggerService.checkTriggers(pctChange);
	}

}
