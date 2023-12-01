package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.UserConverter;
import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Role;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.User;
import com.ndkmusic.repository.PlayListRepository;
import com.ndkmusic.repository.RoleReposity;
import com.ndkmusic.repository.SongRepository;
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

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private PlayListRepository playListRepository;

	@Override
	public UserDTO save(UserDTO userDTO) {
		Role role = roleReposity.findOneByCode(userDTO.getRoleCode());
		User user = userDTO.getId() != null
				? userConverter.toEntity(userDTO, userRepository.findOneByEmail(userDTO.getEmail()))
				: userConverter.toEntity(userDTO);
		if (userDTO.getSongs().size() > 0) {
			for (Long id : userDTO.getSongs()) {
				Song songEntity = songRepository.findOneById(id);
				if (!user.getSongs().contains(songEntity)) {
					user.getSongs().add(songEntity);
				}
			}
		}
		if (userDTO.getPlaylistSongs().size() > 0) {
			for (Long id : userDTO.getPlaylistSongs()) {
				PlayList playList = playListRepository.findOneById(id);
				if (!user.getPlayLists().contains(playList)) {
					user.getPlayLists().add(playList);
				}
			}
		}
		user.setRole(role);
		userRepository.save(user);
		return userConverter.toDTO(user);
	}

}
