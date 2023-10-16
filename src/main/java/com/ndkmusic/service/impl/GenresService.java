package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.GenresConverter;
import com.ndkmusic.dto.GenresDTO;
import com.ndkmusic.entities.Genres;
import com.ndkmusic.repository.GenresRepository;
import com.ndkmusic.service.IGenresService;

@Service
public class GenresService implements IGenresService {

	@Autowired
	private GenresRepository genresRepository;

	@Autowired
	private GenresConverter genresConverter;

	@Override
	public GenresDTO save(GenresDTO genresDTO) {
		// TODO Auto-generated method stub
		Genres genres = genresConverter.toEntity(genresDTO);
		genresRepository.save(genres);
		return genresConverter.toDTO(genres);
	}

}
