package com.ndkmusic.converter;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.GenresDTO;
import com.ndkmusic.entities.Genres;

@Component
public class GenresConverter {
	public Genres toEntity(GenresDTO genresDTO) {
		Genres genres = new Genres();
		genres.setName(genresDTO.getName());
		genres.setCode(genresDTO.getCode());
		return genres;
	}

	public GenresDTO toDTO(Genres genres) {
		GenresDTO genresDTO = new GenresDTO();
		genresDTO.setId(genres.getId());
		genresDTO.setName(genres.getName());
		genresDTO.setCode(genres.getCode());
		genresDTO.setCreatedDate(genres.getCreatedDate());
		genresDTO.setModifiedDate(genres.getModifiedDate());
		return genresDTO;
	}

	public Genres toEntity(GenresDTO genresDTO, Genres genres) {
		genres.setName(genresDTO.getName());
		genres.setCode(genresDTO.getCode());
		return genres;
	}
}
