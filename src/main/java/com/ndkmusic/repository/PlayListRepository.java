package com.ndkmusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Topic;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
	PlayList findOneById(Long id);

	List<PlayList> findByTopic(Topic topic);
}
