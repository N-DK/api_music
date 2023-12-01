package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.TopicConverter;
import com.ndkmusic.dto.TopicDTO;
import com.ndkmusic.entities.Topic;
import com.ndkmusic.repository.TopicRepository;
import com.ndkmusic.service.ITopicService;

@Service
public class TopicService implements ITopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TopicConverter topicConverter;

	@Override
	public TopicDTO save(TopicDTO topicDTO) {
		Topic topic = topicDTO.getId() != null
				? topicConverter.toEntity(topicDTO, topicRepository.findOneById(topicDTO.getId()))
				: topicConverter.toEntity(topicDTO);
		topicRepository.save(topic);
		return topicConverter.toDTO(topic);
	}

	@Override
	public List<TopicDTO> findAll(Pageable pageable) {
		List<TopicDTO> results = new ArrayList<TopicDTO>();
		List<Topic> topics = topicRepository.findAll(pageable).getContent();
		for (Topic topic : topics) {
			results.add(topicConverter.toDTO(topic));
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) topicRepository.count();
	}

	@Override
	public List<TopicDTO> findOneById(long id) {
		List<TopicDTO> result = new ArrayList<TopicDTO>();
		Topic topic = topicRepository.findOneById(id);
		result.add(topicConverter.toDTO(topic));
		return result;
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		for(long id : ids) {
			topicRepository.deleteById(id);
		}
	}

}
