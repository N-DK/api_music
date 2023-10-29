package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<GenresDTO> findAll(Pageable pageable) {
		List<GenresDTO> results = new ArrayList<GenresDTO>();
		List<Genres> genreses = genresRepository.findAll(pageable).getContent();
		for (Genres genres : genreses) {
			results.add(genresConverter.toDTO(genres));
		}
		return results;
	}

	@Override
	public int totalItem() {
		// TODO Auto-generated method stub
		return (int) genresRepository.count();
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			genresRepository.deleteById(id);
		}
	}

}
