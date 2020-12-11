package com.cenfotec.graphql.services;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import com.cenfotec.graphql.entities.Customer;
import com.cenfotec.graphql.repository.CustomerRepository;

import javassist.NotFoundException;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	public List<Customer> getAllCustomers(int count) {
		return this.customerRepo.findAll().stream().limit(count).collect(Collectors.toList());
	}

	public Optional<Customer> getCustomer(int id) {
		return this.customerRepo.findById(id);
	}

	public Customer createCustomer(String fullName, String address, String billingAddress, String cardNumber, String expDate) {

		Customer customer = new Customer();
		customer.setFullName(fullName);
		customer.setAddress(address);
		customer.setBillingAddress(billingAddress);
		customer.setCardNumber(cardNumber);
		customer.setExpDate(LocalDate.parse(expDate));
		return this.customerRepo.save(customer);
	}

	public Boolean deleteCustomer(int id) {
		customerRepo.deleteById(id);
		return true;
	}
	
	public Customer updateCustomer(String id, String fullName, String address, String billingAddress, String cardNumber, String expDate) throws NotFoundException {
	    Optional<Customer> opCustomer = customerRepo.findById(Integer.parseInt(id));

	    if (opCustomer.isPresent()) {
	    	Customer cx = opCustomer.get();

	      if (fullName != null)
	    	  cx.setFullName(fullName);
	      if (address != null)
	    	  cx.setAddress(address);
	      if (expDate != null)
	    	  cx.setExpDate(LocalDate.parse(expDate));
	      if (billingAddress != null)
	    	  cx.setBillingAddress(billingAddress);
	      if (cardNumber != null)
	    	  cx.setCardNumber(cardNumber);

	      customerRepo.save(cx);
	      return cx;
	    }
	    throw new NotFoundException("Customer not found");
	  }
}
