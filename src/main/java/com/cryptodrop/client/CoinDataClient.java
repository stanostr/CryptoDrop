package com.cryptodrop.client;

import java.util.Map;

import com.cryptodrop.domain.Coin;

public interface CoinDataClient {
	Map<String, Coin> getCoinData();
}
