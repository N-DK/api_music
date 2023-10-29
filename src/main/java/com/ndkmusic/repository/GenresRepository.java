package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Genres;

public interface GenresRepository extends JpaRepository<Genres, Long>{
	Genres findOneByCode(String code);
	
	Genres findOneById(long id);
}
