package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.PlayListConverter;
import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.Topic;
import com.ndkmusic.entities.User;
import com.ndkmusic.repository.PlayListRepository;
import com.ndkmusic.repository.SongRepository;
import com.ndkmusic.repository.TopicRepository;
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
	private TopicRepository topicRepository;

	@Autowired
	private PlayListConverter playListConverter;

	@Override
	public PlaylistSong save(PlayListDTO playListDTO) {
		PlayList playList = null;
		if (playListDTO.getId() != null) {
			PlayList oldPlaylistEntity = playListRepository.findOneById(playListDTO.getId());
			playList = playListConverter.toEntity(playListDTO, oldPlaylistEntity);
		} else {
			playList = playListConverter.toEntity(playListDTO);
		}
		User user = userRepository.findOneByEmail(playListDTO.getEmailUser());
		Topic topic = topicRepository.findOneByCode(playListDTO.getTopicCode());
		for (Object favoriteSong : playListDTO.getFavoriteSong()) {
			Song song = songRepository.findOneByTitle(favoriteSong.toString());
			if(song == null) break;
			playList.getSongs().add(song);
		}
		if(user != null && topic != null) {
			playList.setUser(user);
			playList.setTopic(topic);
			playListRepository.save(playList);			
		}
		return playListConverter.toDTO(playList);
	}

	@Override
	public List<PlaylistSong> findAll(Pageable pageable) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		List<PlayList> playlists = playListRepository.findAll(pageable).getContent();
		for (PlayList playList : playlists) {
			results.add(playListConverter.toDTO(playList));
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) playListRepository.count();
	}

	@Override
	public List<PlaylistSong> findOneById(long id) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		PlayList playList = playListRepository.findOneById(id);
		results.add(playListConverter.toDTO(playList));
		return results;
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			playListRepository.deleteById(id);
		}
	}

}
