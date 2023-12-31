package com.ndkmusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
public class SongService implements ISongService {

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
		Song song = songDTO.getId() != null
				? songConverter.toEntity(songDTO, songRepository.findOneById(songDTO.getId()))
				: songConverter.toEntity(songDTO);
		song.setGenres(genres);
//		add artist for song
		for (Object artist : songDTO.getArtists()) {
			Artist artistEntity = artistRepository.findOneByName(artist.toString());
			if (!song.getSongArtists().contains(artistEntity)) {
				song.getSongArtists().add(artistEntity);
				artistEntity.getSongs().add(song);
			}
		}
		if (songDTO.getAlbums().size() == 0) {
			Album album = songConverter.toAlbum(song);
//			add artist if song not in album
			for (Object artist : songDTO.getArtists()) {
				Artist artistEntity = artistRepository.findOneByName(artist.toString());
				if (!album.getAlbumArtists().contains(artistEntity)) {
					album.getAlbumArtists().add(artistEntity);
				}
			}
			if (!song.getAlbums().contains(album)) {
				album.getSongs().add(song);
				song.getAlbums().add(album);
			}
		} else {
			for (Object album : songDTO.getAlbums()) {
				Album albumEntity = albumRepository.findOneByName(album.toString());
				if (!song.getAlbums().contains(albumEntity)) {
					albumEntity.getSongs().add(song);
					song.getAlbums().add(albumEntity);
				}
//				add artist if song in a album
				for (Object artist : songDTO.getArtists()) {
					Artist artistEntity = artistRepository.findOneByName(artist.toString());
					if (!albumEntity.getAlbumArtists().contains(artistEntity)) {
						albumEntity.getAlbumArtists().add(artistEntity);
					}
				}
			}
		}
		songRepository.save(song);
		return songConverter.toDTO(song);
	}

	@Override
	public void delete(long[] ids) {
		for (Long id : ids) {
			songRepository.deleteById(id);
		}
	}

	@Override
	public List<SongDTO> findAll(Pageable pageable) {
		List<SongDTO> results = new ArrayList<SongDTO>();
		List<Song> listSongEntity = songRepository.findAll(pageable).getContent();
		for (Song song : listSongEntity) {
			results.add(songConverter.toDTO(song));
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) songRepository.count();
	}

	@Override
	public List<SongDTO> findOneById(long id) {
		List<SongDTO> result = new ArrayList<SongDTO>();
		Song song = songRepository.findOneById(id);
		result.add(songConverter.toDTO(song));
		return result;
	}

	@Override
	public List<SongDTO> findAll() {
		List<SongDTO> results = new ArrayList<SongDTO>();
		List<Song> listSongEntity = songRepository.findAll();
		for (Song song : listSongEntity) {
			results.add(songConverter.toDTO(song));
		}
		return results;
	}

}
