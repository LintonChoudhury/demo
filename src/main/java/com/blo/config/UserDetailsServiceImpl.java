//package com.blo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.blo.entities.User;
//import com.blo.exception.ResourceNotFoundException;
//import com.blo.repositories.UserRepo;
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	
//	@Autowired
//	private UserRepo userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "User not Found :"+username, 0));
//		return user;
//	}
//
//}
