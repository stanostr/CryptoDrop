package com.cryptodrop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cryptodrop.domain.Trigger;

public interface TriggerRepository extends CrudRepository<Trigger, Long> {
	List<Trigger> findAll();
}
