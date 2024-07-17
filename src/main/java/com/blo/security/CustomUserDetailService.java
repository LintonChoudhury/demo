//package com.blo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.blo.entities.User;
//import com.blo.exception.ResourceNotFoundException;
//import com.blo.repositories.UserRepo;
//
//public class CustomUserDetailService implements UserDetailsService{
//	
//	@Autowired
//	private UserRepo userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// loading user from database by username 
//		User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email:"+username, 0));
//		return user;
//	}
//
//	
//}
