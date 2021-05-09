package com.cryptodrop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cryptodrop.domain.Subscriber;

public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {
	Optional<Subscriber> findFirst1ByEmail(String email);
}
