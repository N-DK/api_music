package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.PlayListConverter;
import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.entities.Artist;
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
		PlayList playList;
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
			if (!playList.getSongs().contains(song)) {
				playList.getSongs().add(song);
			}
		}
		if (user != null) {
			playList.setUser(user);
		}
		if (topic != null) {
			playList.setTopic(topic);
		}
		playListRepository.save(playList);
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

	@Override
	public List<PlaylistSong> findAll(long artist_id) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		List<PlayList> playlists = playListRepository.findAll();
		for (PlayList playList : playlists) {
			boolean isExistArist = false;
			for (Song song : playList.getSongs()) {
				for (Artist artist : song.getSongArtists()) {
					if (artist.getId() == artist_id) {
						isExistArist = true;
					}
				}
			}
			if (isExistArist)
				results.add(playListConverter.toDTO(playList));
			else {
				return results;
			}
		}
		return results;
	}

	@Override
	public List<PlaylistSong> findOneBySlug(String slug) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		List<PlayList> playLists = playListRepository.findAll();
		for (PlayList playList : playLists) {
			if (playList.getTopic() != null) {
				if (playList.getTopic().getCode().equals(slug)) {
					results.add(playListConverter.toDTO(playList));
				}
			}
		}
		return results;
	}

	@Override
	public List<PlaylistSong> findByIdUser(long id) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		List<PlayList> playLists = playListRepository.findAll();
		for (PlayList playList : playLists) {
			if (playList.getUser() != null) {
				if (playList.getUser().getId() == id) {
					results.add(playListConverter.toDTO(playList));
				}
			}
		}
		return results;
	}

}
