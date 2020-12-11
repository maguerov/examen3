package com.cenfotec.parcial3.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.parcial3.model.Customer;
import com.cenfotec.parcial3.repository.CustomerRepository;

@RestController
@RequestMapping({ "/customers" })
public class CustomerController {

	private CustomerRepository repository;

	CustomerController(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Customer> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Customer create(@RequestBody Customer contact) {
		return repository.save(contact);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> update(@PathVariable("id") long id, @RequestBody Customer contact) {
		return repository.findById(id).map(record -> {
			record.setFullName(contact.getFullName());
			record.setAddress(contact.getAddress());
			record.setBillingAddress(contact.getBillingAddress());
			record.setCardNumber(contact.getCardNumber());
			record.setExpDate(contact.getExpDate());
			Customer updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	

	
}
