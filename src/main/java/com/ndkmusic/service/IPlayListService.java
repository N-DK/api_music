package com.ndkmusic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;

public interface IPlayListService {
	PlaylistSong save(PlayListDTO playListDTO);

	List<PlaylistSong> findAll(Pageable pageable);

	List<PlaylistSong> findOneById(long id);

	List<PlaylistSong> findOneBySlug(String slug);

	List<PlaylistSong> findAll(long artist_id);
	
	List<PlaylistSong> findByIdUser(long id);
	int totalItem();

	void delete(long[] ids);
}
