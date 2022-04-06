package com.facebook.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facebook.demo.entity.User;
import com.facebook.demo.repository.UserRepository;


@RestController
@RequestMapping("/users")
@CrossOrigin

public class UserController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	@PostMapping
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@GetMapping("/search/{id}")
	public User findUserById(@PathVariable(value="id") Long Userid) {
		
		return userRepository.findUserById(Userid);
		
	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(value="id") long Userid) {
		 userRepository.deleteById(Userid);
		 return "halo world";
		
		
	}
	@GetMapping("/contain/{input}")
	public List<User> findALLnameContain(@PathVariable(value="input") String input){
		
		return userRepository.contain(input);
	}
	
	
	@PostMapping("/edit/{id}")
	public User updateUser(@PathVariable(value="id") long id,@RequestBody User newUser) {
		System.out.println("the user send is "+newUser.toString());
		User user = userRepository.getById(id);
		System.out.println("the user FOUND is "+user.toString());
		user.setEmail(newUser.getName());
		user.setName(newUser.getEmail());
		user.setPhone(newUser.getPhone());
		user = userRepository.save(user);
		return user;
	}
	
	

}
