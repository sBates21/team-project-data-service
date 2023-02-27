package com.bah.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.data.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}
