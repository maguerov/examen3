package com.cenfotec.parcial3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.parcial3.model.Customer;

@Repository
public interface CustomerRepository
 extends JpaRepository<Customer, Long> { }