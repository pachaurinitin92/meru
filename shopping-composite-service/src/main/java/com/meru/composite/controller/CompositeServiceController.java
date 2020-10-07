package com.meru.composite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meru.composite.entity.User;
import com.meru.composite.feign.UserFeignClient;


@RestController
public class CompositeServiceController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/user/{id}")
	public User getUserDetails(@PathVariable Long id){
		return  userFeignClient.getUserDetails(id);
	}
	
	@PostMapping(value = "/signUp")
    public ResponseEntity<User> addUser(@RequestBody User user){
		return userFeignClient.addUser(user);
	}
	
	@PutMapping(value = "/updateUser/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		return userFeignClient.updateUser(id, user);
	}

}
