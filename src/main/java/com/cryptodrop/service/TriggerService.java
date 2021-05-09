package com.cryptodrop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptodrop.domain.Trigger;
import com.cryptodrop.repository.TriggerRepository;

@Service
public class TriggerService {
	
	@Autowired
	private TriggerRepository triggerRepo;
	
	public List<Trigger> getTriggers()
	{
		return triggerRepo.findAll(); 
	}
	
}
