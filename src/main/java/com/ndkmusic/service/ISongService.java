package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.SongDTO;

public interface ISongService {
	SongDTO save(SongDTO songDTO);

	void delete(long[] ids);

	List<SongDTO> findAll(Pageable pageable);
	
	List<SongDTO> findOneById(long id);

	int totalItem();
}
