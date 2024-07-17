package com.blo.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.blo.entities.Role;
import com.blo.entities.User;
import com.blo.repositories.RoleRepo;
import com.blo.repositories.UserRepo;
import com.blo.services.UserService;
import com.blo.userDto.UserDto;


import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User saveUser = null;
		try {
			User user = mapper.map(userDto, User.class);
			saveUser = userRepo.save(user);
			if (ObjectUtils.isEmpty(saveUser)) {
				return null;
			}

		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		UserDto map = mapper.map(saveUser, UserDto.class);
		return map;
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer id) {
		Optional<User> userOptional = userRepo.findById(userdto.getId());
		
		
		if (userOptional.isPresent()) {
			User user = userOptional.get();

			if (userdto.getName() != null) {
				user.setName(userdto.getName());
			}
			if (userdto.getEmail() != null) {
				user.setEmail(userdto.getEmail());
			}
			if (userdto.getPassword() != null) {
				user.setPassword(userdto.getPassword());
			}
			if(userdto.getAbout()!= null){
				user.setAbout(userdto.getAbout());
			}
		
		User saveUser = userRepo.save(user);
		return mapper.map(saveUser, UserDto.class);
	}
	return userdto;
	}

	@Override
	public UserDto getUserById(Integer id) {
		UserDto userDto = null;
		try {
			User user = userRepo.findById(id).orElseThrow(()-> new com.blo.exception.ResourceNotFoundException("User not found with id = " + id, null, id));
			userDto = mapper.map(user, UserDto.class);

		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userDto = null;

		try {
			List<User> users = userRepo.findAll();


			userDto = users.stream().map(user-> mapper.map(user, UserDto.class)).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(users)) {
				return null;
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		return userDto;
	}

	@Override
	public Boolean deleteDepartment(Integer id) {
		try {
			User user = userRepo.findById(id)
					.orElseThrow(() -> new com.blo.exception.ResourceNotFoundException("Department not found with id" + id, null, id));
			if (ObjectUtils.isEmpty(user)) {
				
				User save = userRepo.save(user);
				if (!ObjectUtils.isEmpty(user) ) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		return false;
	}

//	@Override
//	public UserDto resisterNewUser(UserDto userDto) {
//		User user = mapper.map(userDto, User.class);
//		
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		
//		
//		
//		Role role = this.roleRepo.findById("Normal User").get();
//		
//		user.getRoles().add(role);
//		
//		return mapper.map(newUser, UserDto.class);
//	}
//	

}
