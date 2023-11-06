package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{
	Topic findOneByCode(String code);
}
