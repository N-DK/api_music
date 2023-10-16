package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.PlayListConverter;
import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.User;
import com.ndkmusic.repository.PlayListRepository;
import com.ndkmusic.repository.SongRepository;
import com.ndkmusic.repository.UserRepository;
import com.ndkmusic.service.IPlayListService;

@Service
public class PlayListService implements IPlayListService {

	@Autowired
	private PlayListRepository playListRepository;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlayListConverter playListConverter;

	@Override
	public PlayListDTO save(PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		if (playListDTO.getId() != null) {
			PlayList oldPlaylistEntity = playListRepository.findOneById(playListDTO.getId());
			playList = playListConverter.toEntity(playListDTO, oldPlaylistEntity);
		} else {
			playList = playListConverter.toEntity(playListDTO);
		}
		User user = userRepository.findOneByEmail(playListDTO.getEmailUser());
		Song song = songRepository.findOneByTitle(playListDTO.getFavoriteSong());
		playList.getSongs().add(song);
		song.getPlayLists().add(playList);
		playList.setUser(user);
		playListRepository.save(playList);
		return playListConverter.toDTO(playList);
	}

}
