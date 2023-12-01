package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.AlbumConverter;
import com.ndkmusic.converter.ArtistConverter;
import com.ndkmusic.converter.SongConverter;
import com.ndkmusic.dto.ArtistDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.Song;
import com.ndkmusic.repository.ArtistRepository;
import com.ndkmusic.service.IArtistService;

@Service
public class ArtistService implements IArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private ArtistConverter artistConverter;

	@Autowired
	private SongConverter songConverter;

	@Autowired
	private AlbumConverter albumConverter;

	@Override
	public ArtistDTO save(ArtistDTO artistDTO) {
		Artist artist = artistDTO.getId() != null
				? artistConverter.toEntity(artistDTO, artistRepository.findOneById(artistDTO.getId()))
				: artistConverter.toEntity(artistDTO);
		artistRepository.save(artist);
		return artistConverter.toDTO(artist);
	}

	@Override
	public List<ArtistDTO> findOneById(Long id) {
		Artist artist = artistRepository.findOneById(id);
		List<ArtistDTO> results = new ArrayList<ArtistDTO>();
		results.add(artistConverter.toDTO(artist));
		return results;
	}

	@Override
	public List<ArtistDTO> findAll(Pageable pageable) {
		List<Artist> artists = artistRepository.findAll(pageable).getContent();
		List<ArtistDTO> results = new ArrayList<ArtistDTO>();
		for (Artist artist : artists) {
			results.add(artistConverter.toDTO(artist));
		}
		return results;
	}

	@Override
	public List<Object> getSongs(Long id) {
		List<Object> result = new ArrayList<Object>();
		Artist artist = artistRepository.findOneById(id);
		for (Song song : artist.getSongs()) {
			result.add(songConverter.toDTO(song));
		}
		return result;
	}

	@Override
	public List<Object> getAblums(Long id) {
		List<Object> result = new ArrayList<Object>();
		Artist artist = artistRepository.findOneById(id);
		for (Album album : artist.getAlbums()) {
			result.add(albumConverter.toDTO(album));
		}
		return result;
	}

	@Override
	public int totalItem() {
		return (int) artistRepository.count();
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		for (long id : ids) {
			artistRepository.deleteById(id);
		}

	}

	@Override
	public List<ArtistDTO> findAll() {
		List<Artist> artists = artistRepository.findAll();
		List<ArtistDTO> results = new ArrayList<ArtistDTO>();
		for (Artist artist : artists) {
			results.add(artistConverter.toDTO(artist));
		}
		return results;
	}

}
