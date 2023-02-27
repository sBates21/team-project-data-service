package com.bah.data.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.data.domain.Event;
import com.bah.data.domain.Registration;
import com.bah.data.repository.RegistrationsRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationApi {
ArrayList<Registration> registrationList = new ArrayList<Registration>();
	
	@Autowired
	RegistrationsRepository repo;
	
//	public RegistrationApi() {
//		Registration r1 = new Registration(1L, "1", "2", new Date(), "please email me the Registration details");
//		Registration r2 = new Registration(2L, "2", "2", new Date(), "send transportation and hotel booking");
//		Registration r3 = new Registration(3L, "3", "3", new Date(), "defer payments for a week");
//	
//		registrationList.add(r1);
//		registrationList.add(r2);
//		registrationList.add(r3);
//
//	}
	
	@GetMapping
	public Iterable<Registration> getAll(){
		return repo.findAll();
	}
	
	@GetMapping("/{RegistrationId}")
	public Optional<Registration> getRegistrationById(@PathVariable("RegistrationId") long id) {
		return repo.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration, UriComponentsBuilder uri) {
		if (newRegistration.getId() != 0 || newRegistration.getEvent_id() == null || newRegistration.getCustomer_id() == null ||
					newRegistration.getNotes() == null || newRegistration.getRegistration_date() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration = repo.save(newRegistration);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{registrationId}")
	public ResponseEntity<?> putRegistration(@RequestBody Registration newRegistration,
			@PathVariable("registrationId") long registrationId) {
		if (newRegistration.getId() !=  registrationId || newRegistration.getEvent_id() == null || newRegistration.getCustomer_id()== null ||
				newRegistration.getNotes() == null || newRegistration.getRegistration_date() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration = repo.save(newRegistration);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{registrationId}")
	public ResponseEntity<?> deleteRegistrationById(@PathVariable("registrationId") long id) {

		Registration registrationToDelete = repo.findById(id).orElse(null);
		if (registrationToDelete != null) {
			repo.delete(registrationToDelete);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
