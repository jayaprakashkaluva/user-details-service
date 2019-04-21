package com.jp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.jp.model.user.Address;
import com.jp.model.user.User;

import lombok.AllArgsConstructor;

@Service
public class UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

	public User getUserDetails(String userId) {
		List<Address> addresses = getAddresses(userId);
		return createUser(addresses);
	}

	public List<Address> getAddresses(String userId) {
		ResponseEntity<List<Address>> addresses = restTemplate.exchange(
				"http://shipping-address-service/users/{userId}/shippingAddress", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Address>>() {
				}, userId);
		return addresses.getBody();
	}
	
	private User createUser(List<Address> addresses) {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("k");
		user.setShippingAddress(addresses.iterator().next());
		return user;
	}
}
