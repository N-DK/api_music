package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ISearchService {
	void findAll(String keyword, List<Object> results, Pageable pageable, String query);
}
