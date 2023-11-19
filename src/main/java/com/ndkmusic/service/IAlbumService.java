package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.AlbumDTO;

public interface IAlbumService {
	AlbumDTO save(AlbumDTO albumDTO);

	List<AlbumDTO> findAll(Pageable pageable);
	
	List<AlbumDTO> findAll();

	List<AlbumDTO> findOneById(long id);
	
	int totalItem();
	
	void delete(long[] ids);
}
