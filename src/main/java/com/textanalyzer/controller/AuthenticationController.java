package com.textanalyzer.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.textanalyzer.data.UserData;
import com.textanalyzer.model.ERole;
import com.textanalyzer.model.Role;
import com.textanalyzer.model.User;
import com.textanalyzer.payload.response.JwtResponse;
import com.textanalyzer.security.jwt.JwtUtils;
import com.textanalyzer.security.service.UserDetailsImpl;
import com.textanalyzer.security.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {
	

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/loadInitialData")
	public boolean loadInitialData() {
		
			User user1 = new User("admin",encoder.encode("admin"));
			Set<Role> role1 = new HashSet<>();
			Role r1= new Role(ERole.ROLE_ADMIN);
			role1.add(r1);
			user1.setRoles(role1);
			
			User user2 = new User("deep",encoder.encode("12345"));
			Set<Role> role2 = new HashSet<>();
			Role r2= new Role(ERole.ROLE_USER);
			role2.add(r2);
			user2.setRoles(role2);
			
			UserData.listUsers.add(user1);
			UserData.listUsers.add(user2);
			return true;
	}
	

	@PostMapping("token")
	public ResponseEntity<JwtResponse> getToken(@RequestParam String userName, @RequestParam String password) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userName, password));
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		JwtResponse jwtResponse = new JwtResponse(jwt,userDetails.getUsername(), roles);
		return ResponseEntity.ok(jwtResponse);

	}

	@RequestMapping(value = "/test",method = {RequestMethod.GET,RequestMethod.POST})
	public String test() {
		return "working";
	}
}
