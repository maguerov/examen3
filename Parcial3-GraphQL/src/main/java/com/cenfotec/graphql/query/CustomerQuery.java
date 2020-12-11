package com.cenfotec.graphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.graphql.entities.Customer;
import com.cenfotec.graphql.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class CustomerQuery implements GraphQLQueryResolver {

	@Autowired
	private CustomerService customerService;

	public List<Customer> getCustomers(int count) {
		return this.customerService.getAllCustomers(count);
	}

	public Optional<Customer> getCustomer(int id) {
		return this.customerService.getCustomer(id);
	}

}
