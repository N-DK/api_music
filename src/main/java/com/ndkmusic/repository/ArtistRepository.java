package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
	Artist findOneByName(String name);
}
