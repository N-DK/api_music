package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.ArtistConverter;
import com.ndkmusic.dto.ArtistDTO;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.repository.ArtistRepository;
import com.ndkmusic.service.IArtistService;

@Service
public class ArtistService implements IArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private ArtistConverter artistConverter;

	@Override
	public ArtistDTO save(ArtistDTO artistDTO) {
		Artist artist = artistConverter.toEntity(artistDTO);
		artistRepository.save(artist);
		return artistConverter.toDTO(artistDTO);
	}

}
