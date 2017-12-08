package com.example.cachedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cachedemo.entity.Person;
import com.example.cachedemo.repository.PersonRepository;

@RestController
@RequestMapping(path = "/test/v1")
public class CacheDemoController {
	
	public static final String DEFAULT_KEY = "default";
	
	@Autowired
	private PersonRepository personRepository;
	
	@Cacheable(value="persons", key="#id", unless="#result==null")
	@RequestMapping(method = RequestMethod.GET, path="/persons/{id}")
	public Person getPerson(@PathVariable(name = "id") long id) {
		System.out.println("in the get person method");
		return this.personRepository.findById(id);
	}
	
	@Cacheable(value="persons", key="#root.target.DEFAULT_KEY", unless="#result==null")
	@RequestMapping(method = RequestMethod.GET, path="/persons")
	public List<Person> getPersons() {
		System.out.println("in the get persons method");
		return this.personRepository.findAll();
	}
	
	@CachePut(value = "persons", key="{#person.id, #root.target.DEFAULT_KEY}",  unless="#result==null")
	@RequestMapping(method = RequestMethod.POST, path="/save")
	public Person savePerson(@RequestBody Person person) {
		return this.personRepository.save(person);
	}
	
	public void evictCache() {
		System.out.println("In exict");
	}
	
	// @CacheEvict(value = "persons", key= "#id", allEntries =  true)
	@RequestMapping(method = RequestMethod.GET, path="/delete/{id}")
	public void deletePerson(@PathVariable(value="id") long id) {
		this.personRepository.delete(id);
	}

}
