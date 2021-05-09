package com.cryptodrop.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptodrop.domain.Coin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinGeckoDataClient implements CoinDataClient {
	private static final String BASE_URL = "https://api.coingecko.com/api/v3/simple/price";
	
	@Value("${crypto.coins}")
	private String coins;
	@Value("${crypto.currencies}")
	private String currencies;


	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Map<String, Coin> getCoinData() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL).queryParam("ids", coins)
				.queryParam("vs_currencies", currencies).queryParam("include_last_updated_at", true);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		log.info("Calling the Coin Gecko API with URL: " + builder.toUriString());

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				 new ParameterizedTypeReference<Map<String, Coin>>() {}).getBody();
	}

}
