package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.api.output.UserOutput;
import com.ndkmusic.converter.UserConverter;
import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Role;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.User;
import com.ndkmusic.repository.AlbumRepository;
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

	@Autowired
	private AlbumRepository albumRepository;

	@Override
	public UserDTO save(UserDTO userDTO) {
		Role role = roleReposity.findOneByCode(userDTO.getRoleCode());
		User user = userDTO.getId() != null
				? userConverter.toEntity(userDTO, userRepository.findOneById(userDTO.getId()))
				: userConverter.toEntity(userDTO);
		if (userDTO.getSongs().size() > 0) {
			for (Long id : userDTO.getSongs()) {
				Song songEntity = songRepository.findOneById(id);
				if (!user.getSongs().contains(songEntity)) {
					user.getSongs().add(songEntity);
				}
			}
		}
		if (userDTO.getPlaylist().size() > 0) {
			for (Long id : userDTO.getPlaylist()) {
				PlayList playList = playListRepository.findOneById(id);
				if (!user.getUserPlayLists().contains(playList)) {
					user.getUserPlayLists().add(playList);
				}
			}
		}
		if (userDTO.getAlbum().size() > 0) {
			for (Long id : userDTO.getAlbum()) {
				Album album = albumRepository.findOneById(id);
				if (!user.getAlbums().contains(album)) {
					user.getAlbums().add(album);
				}
			}
		}
		user.setRole(role);
		if (userRepository.findOneById(userDTO.getId()) != null && userDTO.getId() == null) {
			return null;
		}
		userRepository.save(user);
		return userConverter.toDTO(user);
	}

	@Override
	public UserOutput delete(Long id, Long typeId, String type) {
		User user = userRepository.findOneById(id);
		if (type.equals("album")) {
			Album album = albumRepository.findOneById(typeId);
			user.getAlbums().remove(album);
		} else if (type.equals("song")) {
			Song song = songRepository.findOneById(typeId);
			user.getSongs().remove(song);
		} else if (type.equals("playlist")) {
			PlayList playList = playListRepository.findOneById(typeId);
			if (playList.getUser().getId() == id) {
				playListRepository.deleteById(typeId);
			}
			user.getUserPlayLists().remove(playList);
		}
		userRepository.save(user);
		UserOutput userOutput = userConverter.toOutput(user);
		return userOutput;
	}

}
