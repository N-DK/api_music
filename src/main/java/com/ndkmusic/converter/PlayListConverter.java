package com.ndkmusic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.dto.SongArtistId;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;

@Component
public class PlayListConverter {

	@Autowired
	private SongConverter songConverter;

	public PlayList toEntity(PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		playList.setName(playListDTO.getName());
		playList.setThumbnail(playListDTO.getThumbnail());
		return playList;
	}

	public PlaylistSong toDTO(PlayList playList) {
		List<SongDTO> songs = new ArrayList<SongDTO>();
		for (Song song : playList.getSongs()) {
			songs.add(songConverter.toDTO(song));
		}
		PlaylistSong dto = new PlaylistSong(songs, playList.getName());
		dto.setId(playList.getId());
		dto.setModifiedDate(playList.getModifiedDate());
		dto.setModifiedBy(playList.getModifiedBy());
		dto.setCreatedBy(playList.getUser().getEmail());
		dto.setCreatedDate(playList.getCreatedDate());
		dto.setThumbnail(playList.getThumbnail());
		dto.setTopic(playList.getTopic().getName());
		List<SongArtistId> artists = new ArrayList<SongArtistId>();
		for(Song song : playList.getSongs()) {
			for(Artist artist : song.getSongArtists()) {
				SongArtistId songArtistId = new SongArtistId(artist.getId(), artist.getName());
				if(!artists.contains(songArtistId)) {
					artists.add(songArtistId);
				}
			}
		}
		dto.setArtists(artists);
		return dto;
	}

	public PlayList toEntity(PlayListDTO playListDTO, PlayList playList) {
		playList.setName(playListDTO.getName());
		playList.setModifiedBy(playListDTO.getEmailUser());
		playList.setThumbnail(playListDTO.getThumbnail());
		return playList;
	}
}
