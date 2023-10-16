package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long>{
	PlayList findOneById(Long id);
}
