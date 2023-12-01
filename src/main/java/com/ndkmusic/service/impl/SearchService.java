package com.ndkmusic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.dto.AlbumDTO;
import com.ndkmusic.dto.ArtistDTO;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.service.IAlbumService;
import com.ndkmusic.service.IArtistService;
import com.ndkmusic.service.ISearchService;
import com.ndkmusic.service.ISongService;

@Service
public class SearchService implements ISearchService {

	@Autowired
	private ISongService songService;

	@Autowired
	private IAlbumService albumService;

	@Autowired
	private IArtistService artistService;

	@Override
	public void findAll(String keyword, List<Object> results, Pageable pageable, String query) {
		switch (keyword) {
		case "song":
			for (SongDTO song : songService.findAll()) {
				if (song.getTitle().contains(query))
					results.add(song);
			}
			break;
		case "album":
			for (AlbumDTO album : albumService.findAll()) {
				if (album.getName().contains(query))
					results.add(album);
			}
			break;
		case "artist":
			for (ArtistDTO artist : artistService.findAll()) {
				if (artist.getArtistName().contains(query))
					results.add(artist);
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + keyword);
		}
	}
	
	public void findAll(String keyword, List<Object> results, String query) {
		switch (keyword) {
		case "song":
			for (SongDTO song : songService.findAll()) {
				if (song.getTitle().contains(query))
					results.add(song);
			}
			break;
		case "album":
			for (AlbumDTO album : albumService.findAll()) {
				if (album.getName().contains(query))
					results.add(album);
			}
			break;
		case "artist":
			for (ArtistDTO artist : artistService.findAll()) {
				if (artist.getArtistName().contains(query))
					results.add(artist);
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + keyword);
		}
	}

}
