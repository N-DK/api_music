package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.TopicDTO;

public interface ITopicService {
	TopicDTO save(TopicDTO topicDTO);

	List<TopicDTO> findAll(Pageable pageable);

	List<TopicDTO> findOneById(long id);

	int totalItem();
}
