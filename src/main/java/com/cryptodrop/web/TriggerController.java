package com.cryptodrop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptodrop.domain.Trigger;
import com.cryptodrop.service.TriggerService;

@RestController
@RequestMapping("/triggers")
public class TriggerController {
	@Autowired
	private TriggerService triggerService;
	
	@GetMapping
	public List<Trigger> getTriggers()
	{
		return triggerService.getTriggers();
	}
}
