package com.example.cachedemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.cachedemo.entity.Person;

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	public Person findById(long id);
	
	public Person findById(Long id);
	
	public List<Person> findAll();

}