package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Song;

public interface SongRepository extends JpaRepository<Song, Long>{
	
}
