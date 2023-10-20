package com.ndkmusic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.AlbumDTO;
import com.ndkmusic.dto.AlbumSong;
import com.ndkmusic.dto.SongArtistId;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.Song;

@Component
public class AlbumConverter {

	public Album toEntity(AlbumDTO albumDTO) {
		Album album = new Album();
		album.setName(albumDTO.getName());
		album.setThumbnail(albumDTO.getThumbnail());
		return album;
	}

	public AlbumDTO toDTO(Album album) {
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(album.getId());
		albumDTO.setCreatedDate(album.getCreatedDate());
		albumDTO.setCreatedBy(album.getCreatedBy());
		albumDTO.setModifiedDate(album.getModifiedDate());
		albumDTO.setModifiedBy(album.getModifiedBy());
		albumDTO.setName(album.getName());
		albumDTO.setThumbnail(album.getThumbnail());
		albumDTO.setTotalListen(album.getTotalListen() == null ? 0 : album.getTotalListen());
		albumDTO.setGenresCode(album.getGenres().getCode());
		List<Object> songs = new ArrayList<Object>();
		List<Object> artists = new ArrayList<Object>();
		for (Artist artist : album.getAlbumArtists()) {
			artists.add(new SongArtistId(artist.getId(), artist.getName()));
		}
		for (Song song : album.getSongs()) {
			List<Object> artistsSong = new ArrayList<Object>();
			for (Artist artist : song.getSongArtists()) {
				artistsSong.add(new SongArtistId(artist.getId(), artist.getName()));
			}
			AlbumSong albumSong = new AlbumSong(song.getId(), song.getTitle(), song.getAudioUrl(), song.getThumbnail(),
					song.getLyric(), song.getTimePlay(), song.getTotalListen(), song.getGenres().getCode(),
					artistsSong);
			songs.add(albumSong);
		}
		albumDTO.setSongs(songs);
		albumDTO.setArtists(artists);
		return albumDTO;
	}
}
