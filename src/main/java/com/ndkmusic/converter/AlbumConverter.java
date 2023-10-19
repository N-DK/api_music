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
		albumDTO.setId(album.getId());
		albumDTO.setCreatedDate(album.getCreatedDate());
		albumDTO.setCreatedBy(album.getCreatedBy());
		albumDTO.setModifiedDate(album.getModifiedDate());
		albumDTO.setModifiedBy(album.getModifiedBy());
		albumDTO.setName(album.getName());
		albumDTO.setThumbnail(album.getThumbnail());
		albumDTO.setTotalListen(album.getTotalListen() == null ? 0 : album.getTotalListen());
		albumDTO.setGenresCode(album.getGenres().getCode());
		return albumDTO;
	}
}
