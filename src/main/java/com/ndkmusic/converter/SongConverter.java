package com.ndkmusic.converter;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Song;

@Component
public class SongConverter {

	public Song toEntity(SongDTO songDTO) {
		Song song = new Song();
		song.setTitle(songDTO.getTitle());
		song.setLyric(songDTO.getLyric());
		song.setAudioUrl(songDTO.getAudioUrl());
		song.setThumbnail(songDTO.getThumbnail());
		song.setTimePlay(songDTO.getTimePlay());
		return song;
	}

	public SongDTO toDTO(Song song) {
		SongDTO songDTO = new SongDTO();
		songDTO.setTitle(song.getTitle());
		songDTO.setLyric(song.getLyric());
		songDTO.setAudioUrl(song.getAudioUrl());
		songDTO.setThumbnail(song.getThumbnail());
		songDTO.setTimePlay(song.getTimePlay());
		songDTO.setModifiedDate(song.getModifiedDate());
		songDTO.setModifiedBy(song.getModifiedBy());
		songDTO.setCreatedBy(song.getCreatedBy());
		songDTO.setCreatedDate(song.getCreatedDate());
		return songDTO;
	}

	public Album toAlbum(Song song) {
		Album album = new Album();
		album.setName(song.getTitle() + "(Single)");
		album.setThumbnail(song.getThumbnail());
		album.setGenres(song.getGenres());
		return album;
	}
}
