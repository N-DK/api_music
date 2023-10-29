package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
		Album album = albumDTO.getId() != null
				? albumConverter.toEntity(albumDTO, albumRepository.findOneById(albumDTO.getId()))
				: albumConverter.toEntity(albumDTO);
		album.setGenres(genres);
		for (Object artist : albumDTO.getArtists()) {
			Artist artistEntity = artistRepository.findOneByName(artist.toString());
			album.getAlbumArtists().add(artistEntity);
		}
		albumRepository.save(album);
		return albumConverter.toDTO(album);
	}

	@Override
	public List<AlbumDTO> findAll(Pageable pageable) {
		List<Album> albums = albumRepository.findAll(pageable).getContent();
		List<AlbumDTO> result = new ArrayList<AlbumDTO>();
		for (Album album : albums) {
			result.add(albumConverter.toDTO(album));
		}
		return result;
	}

	@Override
	public List<AlbumDTO> findOneById(long id) {
		Album album = albumRepository.findOneById(id);
		List<AlbumDTO> result = new ArrayList<AlbumDTO>();
		result.add(albumConverter.toDTO(album));
		return result;
	}

	@Override
	public int totalItem() {
		return (int) albumRepository.count();
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		for (long id : ids) {
			albumRepository.deleteById(id);
		}
	}

}
