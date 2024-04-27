package com.textanalyzer.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.textanalyzer.data.UserData;
import com.textanalyzer.model.ERole;
import com.textanalyzer.model.Role;
import com.textanalyzer.model.User;

import ch.qos.logback.core.encoder.Encoder;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		for (User user1 : UserData.listUsers) {
			if(user1.getUsername().toLowerCase().equals(username.toLowerCase())){
				user = user1;
				break;
			}
			
		}
		
		if(user == null ) {
			throw new UsernameNotFoundException("User Not Found with username: " + username); 
		}
		
		return UserDetailsImpl.build(user);
	}

}
