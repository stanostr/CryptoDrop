package com.cryptodrop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptodrop.domain.Trigger;
import com.cryptodrop.repository.TriggerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TriggerService {
	
	@Autowired
	private TriggerRepository triggerRepo;
	
	public List<Trigger> getTriggers()
	{
		return triggerRepo.findAll(); 
	}
	
	public void checkTriggers(Map<String, Double[]> pctChange)
	{
		List<Trigger> triggers = getTriggers();
		for(Trigger trigger: triggers)
		{
			if(pctChange.get(trigger.getCoin())==null) continue;
			Double[] prices = pctChange.get(trigger.getCoin());
			if(trigger.getFrequency() == 5 && Math.abs(prices[0]) >= trigger.getRateChange()) log.info("trigger " + trigger.getId() + " set off");
			else if(trigger.getFrequency() == 10 && Math.abs(prices[1]) >= trigger.getRateChange()) log.info("trigger " + trigger.getId() + " set off");
			else if(trigger.getFrequency() == 15 && Math.abs(prices[2]) >= trigger.getRateChange()) log.info("trigger " + trigger.getId() + " set off");
		}
		
	}
	
}
