package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.api.output.PlaylistToCategoryOutput;
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
		List<Song> songs = new ArrayList<Song>();
		for (Object favoriteSong : playListDTO.getFavoriteSong()) {
			Song song = songRepository.findOneByTitle(favoriteSong.toString());
			if(!songs.contains(song)) {
				songs.add(song);
			}
		}
		playList.setSongs(songs);
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

	@Override
	public List<PlaylistToCategoryOutput> findAllGenresByTopic(String topic) {
		List<PlaylistToCategoryOutput> resutls = new ArrayList<PlaylistToCategoryOutput>();
		List<PlayList> playLists = playListRepository.findByTopic(topicRepository.findOneByCode(topic));
		List<String> categories = new ArrayList<String>();
		for (PlayList playList : playLists) {
			if (!categories.contains(playList.getSubTitle())) {
				categories.add(playList.getSubTitle());
			}
		}

		for (String category : categories) {
			List<PlaylistSong> playlistSongs = new ArrayList<PlaylistSong>();
			for (PlayList playList : playLists) {
				if (playList.getSubTitle().equals(category)) {
					playlistSongs.add(playListConverter.toDTO(playList));
				}
			}
			PlaylistToCategoryOutput output = new PlaylistToCategoryOutput(category, playlistSongs);
			resutls.add(output);
		}
		return resutls;
	}

	@Override
	public List<PlaylistSong> findByIdArtist(long id) {
		List<PlaylistSong> results = new ArrayList<PlaylistSong>();
		List<PlayList> playLists = playListRepository.findAll();
		for (PlayList playList : playLists) {
			for (Song song : playList.getSongs()) {
				for (Artist artist : song.getSongArtists()) {
					if (artist.getId() == id && playList.getPreface() != null) {
						if (!contains(results, playListConverter.toDTO(playList))) {
							results.add(playListConverter.toDTO(playList));
						}
					}
				}
			}
		}
		return results;
	}

	private boolean contains(List<PlaylistSong> results, PlaylistSong playlist) {
		for (PlaylistSong playlistSong : results) {
			if (playlistSong.getId() == playlist.getId()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void deteleSongInPlaylist(long userId, long songId, long playlistId) {
		PlayList playList = playListRepository.findOneById(playlistId);
		if (playList.getUser() != null) {
			if (playList.getUser().getId() == userId) {
				playList.getSongs().removeIf(song -> song.getId() == songId);
				playListRepository.save(playList);
			}
		}
	}

	@Override
	public PlaylistSong add(PlayListDTO playListDTO) {
		PlayList oldPlaylistEntity = playListRepository.findOneById(playListDTO.getId());
		PlayList playList = playListConverter.toEntity(playListDTO, oldPlaylistEntity);
		for (Object favoriteSong : playListDTO.getFavoriteSong()) {
			Song song = songRepository.findOneByTitle(favoriteSong.toString());
			if(!playList.getSongs().contains(song)) {
				playList.getSongs().add(song);
			}
		}
		playListRepository.save(playList);
		return playListConverter.toDTO(playList);
	}

}
