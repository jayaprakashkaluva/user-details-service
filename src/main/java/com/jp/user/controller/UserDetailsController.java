package com.jp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jp.model.user.User;
import com.jp.user.service.UserDetailsService;

import lombok.AllArgsConstructor;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailService;
	
	@GetMapping("/users/{userId}")
	public User getUserDetails(@PathVariable String userId) {
		return userDetailService.getUserDetails(userId);
	}
}
