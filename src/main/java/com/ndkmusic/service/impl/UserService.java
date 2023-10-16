package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.UserConverter;
import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.Role;
import com.ndkmusic.entities.User;
import com.ndkmusic.repository.RoleReposity;
import com.ndkmusic.repository.UserRepository;
import com.ndkmusic.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleReposity roleReposity;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDTO save(UserDTO userDTO) {
		Role role = roleReposity.findOneByCode(userDTO.getRoleCode());
		User user = userConverter.toEntity(userDTO);
		user.setRole(role);
		userRepository.save(user);
		return userConverter.toDTO(user);
	}

}
