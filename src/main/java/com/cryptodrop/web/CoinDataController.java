package com.cryptodrop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptodrop.client.CoinDataClient;
import com.cryptodrop.domain.Coin;

@RestController
@RequestMapping(path = "/coins")
public class CoinDataController {

	@Autowired
	private CoinDataClient client;
	
	@GetMapping("/get")
	public Coin getCoinById(@RequestParam String id) {
		return client.getCoinData().get(id);
	}

}
