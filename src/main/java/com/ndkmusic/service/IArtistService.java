package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.ArtistDTO;

public interface IArtistService {
	ArtistDTO save(ArtistDTO artistDTO);

	ArtistDTO subscribe(Long id, Long user_id);
	
	ArtistDTO unsubscribe(Long id, Long user_id);

	List<ArtistDTO> findOneById(Long id);

	List<ArtistDTO> findAll(Pageable pageable);

	List<ArtistDTO> findAll();

	List<Object> getSongs(Long id);

	List<Object> getAblums(Long id);

	int totalItem();

	void delete(long[] ids);
}
