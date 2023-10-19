package com.ndkmusic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Song;

@Component
public class SongConverter {

	@Autowired
	private AlbumConverter albumConverter;
	
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
		songDTO.setId(song.getId());
		songDTO.setTitle(song.getTitle());
		songDTO.setLyric(song.getLyric());
		songDTO.setAudioUrl(song.getAudioUrl());
		songDTO.setThumbnail(song.getThumbnail());
		songDTO.setTimePlay(song.getTimePlay());
		songDTO.setModifiedDate(song.getModifiedDate());
		songDTO.setModifiedBy(song.getModifiedBy());
		songDTO.setCreatedBy(song.getCreatedBy());
		songDTO.setCreatedDate(song.getCreatedDate());
		songDTO.setGenresCode(song.getGenres().getCode());
		songDTO.setTotalListen(song.getTotalListen() == null ? 0 : song.getTotalListen());
		List<Object> artists = createList(song.getSongArtists());
		List<Object> albums = new ArrayList<Object>();
		for (Album album : song.getAlbums()) {
			albums.add(albumConverter.toDTO(album));
		}
		songDTO.setArtists(artists);
		songDTO.setAlbums(albums);
		return songDTO;
	}

	public Album toAlbum(Song song) {
		Album album = new Album();
		album.setTotalListen(song.getTotalListen());
		album.setName(song.getTitle() + " (Single)");
		album.setThumbnail(song.getThumbnail());
		album.setGenres(song.getGenres());
		return album;
	}

	private <T> List<Object> createList(List<T> list) {
		List<Object> objects = new ArrayList<Object>();
		for (T item : list) {
			objects.add(item);
		}
		return objects;
	}
}
