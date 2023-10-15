package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.SongConverter;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.Genres;
import com.ndkmusic.entities.Song;
import com.ndkmusic.repository.AlbumRepository;
import com.ndkmusic.repository.ArtistRepository;
import com.ndkmusic.repository.GenresRepository;
import com.ndkmusic.repository.SongRepository;
import com.ndkmusic.service.ISongService;

@Service
public class SongService implements ISongService{
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private GenresRepository genresRepository;
	
	@Autowired
	private SongConverter songConverter;

	@Override
	public SongDTO save(SongDTO songDTO) {
		Genres genres = genresRepository.findOneByCode(songDTO.getGenresCode());
		Song song = songConverter.toEntity(songDTO);
		song.setGenres(genres);
		for (String artist : songDTO.getArtists()) {
			Artist artistEntity = artistRepository.findOneByName(artist);
			song.getSongArtists().add(artistEntity);
		}
		if(songDTO.getAlbums().size() == 0) {
			Album album = songConverter.toAlbum(song);
			song.getAlbums().add(album);
		} else {
			for(String album : songDTO.getAlbums()) {
				Album albumEntity = albumRepository.findOneByName(album);
				song.getAlbums().add(albumEntity);
			}
		}
		songRepository.save(song);
		return songConverter.toDTO(song);
	}
	
	
}
