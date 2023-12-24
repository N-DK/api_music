package com.ndkmusic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ndkmusic.dto.ArtistDTO;
import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;

@Component
public class PlayListConverter {

	@Autowired
	private SongConverter songConverter;

	@Autowired
	private ArtistConverter artistConverter;

	private boolean contains(List<ArtistDTO> artists, ArtistDTO artist) {
		for (ArtistDTO artistDTO : artists) {
			if (artistDTO.getId() == artist.getId()) {
				return true;
			}
		}
		return false;
	}

	public PlayList toEntity(PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		playList.setPreface(playListDTO.getPreface());
		playList.setName(playListDTO.getName());
		playList.setSubTitle(playListDTO.getSubTitle());
		playList.setThumbnail(playListDTO.getThumbnail().equals("") ? "https://photo-zmp3.zmdcdn.me/album_default.png"
				: playListDTO.getThumbnail());
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
		dto.setSubTitle(playList.getSubTitle());
		dto.setPreface(playList.getPreface());
		if (playList.getTopic() != null) {
			dto.setTopic(playList.getTopic().getName());
		}
		List<ArtistDTO> artists = new ArrayList<ArtistDTO>();
		for (Song song : playList.getSongs()) {
			for (Artist artist : song.getSongArtists()) {
				if (!contains(artists, artistConverter.toDTO(artist))) {
					artists.add(artistConverter.toDTO(artist));
				}
			}
		}
		dto.setArtists(artists);
		return dto;
	}

	public PlayList toEntity(PlayListDTO playListDTO, PlayList playList) {
		if (playListDTO.getName() != null) {
			playList.setName(playListDTO.getName());
		}
		if (playListDTO.getSubTitle() != null) {
			playList.setSubTitle(playListDTO.getSubTitle());
		}
		if (playListDTO.getThumbnail() != null) {
			playList.setModifiedBy(playListDTO.getEmailUser());
		}
		if(playListDTO.getPreface() != null) {
			playList.setPreface(playListDTO.getPreface());
		}
		playList.setThumbnail(playListDTO.getThumbnail());
		return playList;
	}
}
