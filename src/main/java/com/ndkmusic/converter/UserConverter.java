package com.ndkmusic.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.User;

@Component
public class UserConverter {

	public User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setNickName(userDTO.getNickName());
		user.setBirthday(userDTO.getBirthday());
		user.setAvatar(userDTO.getAvatar());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPasswrord());
		return user;
	}

	public UserDTO toDTO(User user) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		UserDTO userDTO = new UserDTO();
		userDTO.setNickName(user.getNickName());
		userDTO.setBirthday(String.format("%s", dateFormat.format(user.getBirthday())));
		userDTO.setAvatar(user.getAvatar());
		userDTO.setEmail(user.getEmail());
		userDTO.setPasswrord(user.getPassword());
		return userDTO;
	}
}
