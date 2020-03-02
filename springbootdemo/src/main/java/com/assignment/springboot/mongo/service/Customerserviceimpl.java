package com.assignment.springboot.mongo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.mongo.dao.Customerdao;
import com.assignment.springboot.mongo.model.Customer;

@Service
public class Customerserviceimpl implements Customerservice {

	@Autowired
	Customerdao dao;

	@Override
	public void createCustomer(List<Customer> cust) {
		dao.saveAll(cust);
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		return dao.findAll();
	}


	@Override
	public Customer findCustomerById(int id) {
		return dao.findById(id);
	}


	@Override
	public void deleteCustomerById(int id) {
		dao.deleteById(id);
	}


	@Override
	public void updateCustomer(Customer cust) {
		dao.save(cust);
	}


	@Override
	public void deleteAllCustomers() {
		dao.deleteAll();
	}
}