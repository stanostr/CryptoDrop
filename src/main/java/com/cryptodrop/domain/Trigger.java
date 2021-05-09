package com.cryptodrop.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Trigger {
	@Id
	private long id;

	@Column(name = "freq")
	private int frequency;

	@Column(name = "rate_change")
	private double rateChange;

	@ManyToMany(targetEntity = Subscriber.class)
	private List<Subscriber> subscribers;

}
