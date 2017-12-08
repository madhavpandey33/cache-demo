package com.example.cachedemo.entity.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.cache.annotation.CacheEvict;

import com.example.cachedemo.entity.Person;

public class PersonLoggerListener {
	
	
	//private CacheManager cacheManager;
	
	@PrePersist
	public void methodInvokedBeforePersist(Person person) {
		System.out.println("persisting Person with id = " + person.getFirstName());
	}

	@PostPersist
	@CacheEvict(value = "persons", allEntries =  true)
	public void methodInvokedAfterPersist(Person person) {
		System.out.println("Persisted Person with id = " + person.getFirstName());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(Person person) {
		System.out.println("Updating Person with id = " + person.getFirstName());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(Person person) {
		System.out.println("Updated Person with id = " + person.getFirstName());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(Person person) {
		System.out.println("Removing Person with id = " + person.getFirstName());
	}

	@PostRemove
	public void methodInvokedAfterRemove(Person person) {
		clearCache(person.getId());
		// System.out.println(this.cacheManager.getCacheNames());
		System.out.println("Removed Person with id = " + person.getFirstName() );
	}

	
	@CacheEvict(value = "persons", key= "#id", allEntries =  true)
	public void clearCache(long id) {
	}
	
}
