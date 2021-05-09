package com.cryptodrop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptodrop.domain.Subscriber;
import com.cryptodrop.repository.SubscriberRepository;

@Service
public class SubscriberService {
	@Autowired
	private SubscriberRepository repository;
	
	public Subscriber addSubscriber(Subscriber sub)
	{
		return repository.save(sub);
	}
	
	public void deleteSubscriber(String email)
	{
		repository.findFirst1ByEmail(email).ifPresent(repository::delete);
	}
	
	public Subscriber getSubscriber(String email)
	{
		return repository.findFirst1ByEmail(email).orElseThrow();
	}

}
