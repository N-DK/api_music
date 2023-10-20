package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	Album findOneByName(String name);

	Album findOneById(long id);
}
