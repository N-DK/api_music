package com.ndkmusic.converter;


import org.springframework.stereotype.Component;

import com.ndkmusic.dto.TopicDTO;
import com.ndkmusic.entities.Topic;

@Component
public class TopicConverter {
	public Topic toEntity(TopicDTO topicDTO) {
		Topic topic = new Topic();
		topic.setName(topicDTO.getName());
		topic.setCode(topicDTO.getCode());
		return topic;
	}
	
	public TopicDTO toDTO(Topic topic) {
		TopicDTO dto = new TopicDTO();
		dto.setId(topic.getId());
		dto.setName(topic.getName());
		dto.setCode(topic.getCode());
		dto.setCreatedDate(topic.getCreatedDate());
		dto.setModifiedDate(topic.getModifiedDate());
		return dto;
	}
}
