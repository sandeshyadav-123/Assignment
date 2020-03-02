package com.assignment.springboot.mongo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.assignment.springboot.mongo.model.Customer;

@Repository
public interface Customerdao extends JpaRepository<Customer, Integer> {
	Customer findById(int id);

}