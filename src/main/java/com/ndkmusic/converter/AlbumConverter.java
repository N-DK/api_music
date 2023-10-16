package com.ndkmusic.converter;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.AlbumDTO;
import com.ndkmusic.entities.Album;

@Component
public class AlbumConverter {
	public Album toEntity(AlbumDTO albumDTO) {
		Album album = new Album();
		
		album.setName(albumDTO.getName());
		album.setThumbnail(albumDTO.getThumbnail());
		return album;
	}
	
	public AlbumDTO toDTO(Album album) {
		AlbumDTO albumDTO = new AlbumDTO();
		
		albumDTO.setName(album.getName());
		albumDTO.setThumbnail(album.getThumbnail());
		return albumDTO;
	}
}
