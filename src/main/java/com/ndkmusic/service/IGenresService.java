package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.GenresDTO;

public interface IGenresService {
	GenresDTO save(GenresDTO genresDTO);

	List<GenresDTO> findAll(Pageable pageable);
	
	int totalItem();
}
