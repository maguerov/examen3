package com.cenfotec.graphql.mutation;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.graphql.entities.Customer;
import com.cenfotec.graphql.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import javassist.NotFoundException;

@Component
public class CustomerMutation implements GraphQLMutationResolver {

	@Autowired
	private CustomerService customerService;

	public Customer createCustomer(String fullName, String address, String billingAddress, String cardNumber, String expDate) {
	 return this.customerService.createCustomer(fullName, address,
			 billingAddress, cardNumber, expDate);
	}
	
	 public boolean deleteCustomer(int id) {
		 customerService.deleteCustomer(id);
		 return true;
	}
	 
	 public Customer updateCustomer(String id, String fullName, String address, String billingAddress, String cardNumber, String expDate) throws NotFoundException {
	    	 return this.customerService.updateCustomer(id, fullName, address,
	    			 billingAddress, cardNumber, expDate);
	    }

}
