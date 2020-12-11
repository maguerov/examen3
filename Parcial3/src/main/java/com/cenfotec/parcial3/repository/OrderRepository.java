package com.cenfotec.parcial3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.parcial3.model.OrderTable;

@Repository
public interface OrderRepository
 extends JpaRepository<OrderTable, Long> { 
	

}