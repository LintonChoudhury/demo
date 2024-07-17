//package com.blo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.blo.security.JwtAuthenticationEntryPoint;
//import com.blo.security.JwtAuthenticationFilter;
//
//public class SecurityConfig {
//
//	@Autowired
//	private AuthenticationSuccessHandler authenticationSuccessHandler;
//	
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
// 
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserDetailsServiceImpl();
//	}
// 
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
// 
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
//	{
//		http.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
//				.authorizeHttpRequests(req->req.requestMatchers("/user/**").hasRole("USER")
//				.requestMatchers("/admin/**").hasRole("ADMIN")
//				.requestMatchers("/**").permitAll())
//				.formLogin(form->form.loginPage("/signin")
//						.loginProcessingUrl("/login")
////						.defaultSuccessUrl("/")
//						.and()
//						.exceptionHandling()
//						.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//						.and()
//						.sessionManagement()
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//						.successHandler(authenticationSuccessHandler))
//				.logout(logout->logout.permitAll());
//		
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}
//}
