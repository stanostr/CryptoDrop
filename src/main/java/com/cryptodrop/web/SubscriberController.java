package com.cryptodrop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptodrop.domain.Subscriber;
import com.cryptodrop.service.SubscriberService;

@RestController
@RequestMapping(path = "/sub")
public class SubscriberController {
	@Autowired
	private SubscriberService service;

	@PostMapping("/add")
	public Subscriber addSubscriber(@RequestBody Subscriber sub) {
		return service.addSubscriber(sub);
	}

	@DeleteMapping("/delete")
	public void deleteSubscriber(@RequestParam String email) {
		service.deleteSubscriber(email);
	}

	@GetMapping("/get")
	public Subscriber getSubscriberById(@RequestParam String email) {
		return service.getSubscriber(email);
	}

}
