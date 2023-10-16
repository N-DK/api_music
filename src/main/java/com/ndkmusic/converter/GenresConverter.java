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
		genresDTO.setName(genres.getName());
		genresDTO.setCode(genres.getCode());
		return genresDTO;
	}
}
