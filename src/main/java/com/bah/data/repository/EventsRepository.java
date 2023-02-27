package com.bah.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bah.data.domain.Event;

public interface EventsRepository extends CrudRepository<Event, Long> {

}