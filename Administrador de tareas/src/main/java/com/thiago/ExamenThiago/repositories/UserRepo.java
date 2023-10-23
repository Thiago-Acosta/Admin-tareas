package com.thiago.ExamenThiago.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thiago.ExamenThiago.models.User;


public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	List <User> findAll();
	

}