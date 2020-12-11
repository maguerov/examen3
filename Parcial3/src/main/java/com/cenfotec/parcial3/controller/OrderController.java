package com.cenfotec.parcial3.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.parcial3.model.OrderTable;
import com.cenfotec.parcial3.repository.OrderRepository;

@RestController
@RequestMapping({ "/orders" })
public class OrderController {

	private OrderRepository repository;

	OrderController(OrderRepository customerRepository) {
		this.repository = customerRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<OrderTable> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public OrderTable create(@RequestBody OrderTable contact) {
		return repository.save(contact);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrderTable> update(@PathVariable("id") long id, @RequestBody OrderTable contact) {
		return repository.findById(id).map(record -> {
			record.setProductType(contact.getProductType());
			record.setQuantityAtHand(contact.getQuantityAtHand());
			record.setIdCustomer(contact.getIdCustomer());
			OrderTable updated = repository.save(record);
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
	
	
//	@GetMapping(path = { "/{type}" })
//	public List<OrderTable> findById(@PathVariable String type) {
//		return repository.findByNameContaining(type);
//	}
}
