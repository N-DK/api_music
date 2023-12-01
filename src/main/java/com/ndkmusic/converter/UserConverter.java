package com.ndkmusic.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.User;

@Component
public class UserConverter {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setNickName(userDTO.getNickName());
		user.setBirthday(userDTO.getBirthday());
		user.setAvatar(userDTO.getAvatar());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPasswrord()));
		return user;
	}

	public UserDTO toDTO(User user) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		UserDTO userDTO = new UserDTO();
		userDTO.setNickName(user.getNickName());
		userDTO.setCreatedBy(user.getCreatedBy());
		userDTO.setModifiedBy(user.getModifiedBy());
		userDTO.setModifiedDate(user.getModifiedDate());
		userDTO.setBirthday(String.format("%s", dateFormat.format(user.getBirthday())));
		userDTO.setAvatar(user.getAvatar());
		userDTO.setEmail(user.getEmail());
		userDTO.setPasswrord(user.getPassword());
		userDTO.setId(user.getId());
		userDTO.setCreatedDate(user.getCreatedDate());
		userDTO.setRoleCode(user.getRole().getCode());
		for (Song song : user.getSongs()) {
			userDTO.getSongs().add(song.getId());
		}
		for (PlayList playList : user.getPlayLists()) {
			userDTO.getPlaylistSongs().add(playList.getId());
		}
		return userDTO;
	}

	public User toEntity(UserDTO userDTO, User user) {
		user.setNickName(userDTO.getNickName());
		user.setBirthday(userDTO.getBirthday());
		user.setAvatar(userDTO.getAvatar());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPasswrord()));
		return user;
	}
}
