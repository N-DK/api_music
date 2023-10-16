package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.AlbumConverter;
import com.ndkmusic.dto.AlbumDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.Genres;
import com.ndkmusic.repository.AlbumRepository;
import com.ndkmusic.repository.ArtistRepository;
import com.ndkmusic.repository.GenresRepository;
import com.ndkmusic.service.IAlbumService;

@Service
public class AlbumService implements IAlbumService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private GenresRepository genresRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private AlbumConverter albumConverter;

	@Override
	public AlbumDTO save(AlbumDTO albumDTO) {
		Genres genres = genresRepository.findOneByCode(albumDTO.getGenresCode());
		Album album = albumConverter.toEntity(albumDTO);
		album.setGenres(genres);
		for (String artist : albumDTO.getArtists()) {
			Artist artistEntity = artistRepository.findOneByName(artist);
			album.getAlbumArtists().add(artistEntity);
		}
		albumRepository.save(album);
		// TODO Auto-generated method stub
		return albumConverter.toDTO(album);
	}

}
