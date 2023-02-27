package com.bah.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.data.domain.Registration;

public interface RegistrationsRepository extends CrudRepository<Registration, Long> {

}