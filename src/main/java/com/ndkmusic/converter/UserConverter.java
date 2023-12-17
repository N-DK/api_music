package com.ndkmusic.converter;

import static com.ndkmusic.utils.UploadToCloud.*;

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
		userDTO.setAvatar(
				createLinkFromCloud(userDTO.getAvatar(), "video", "ndk_music/avatar/" + userDTO.getNickName()));
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
		for (Song song : user.getSongs()) {
			userDTO.getSongs().add(song.getId());
		}
		for (PlayList playList : user.getPlayLists()) {
			userDTO.getPlaylist().add(playList.getId());
		}
		return userDTO;
	}

	public User toEntity(UserDTO userDTO, User user) {

		if (userDTO.getNickName() != null) {
			user.setNickName(userDTO.getNickName());
		}
		if (userDTO.getBirthday() != null) {
			user.setBirthday(userDTO.getBirthday());
		}
		if (userDTO.getAvatar() != null) {
			userDTO.setAvatar(createSecureLinkFromBlobURL(userDTO.getAvatar(), "image",
					"ndk_music/avatar/" + user.getNickName()));
			user.setAvatar(userDTO.getAvatar());
		}
		if (userDTO.getEmail() != null) {
			user.setEmail(userDTO.getEmail());
		}
		return user;
	}
}
