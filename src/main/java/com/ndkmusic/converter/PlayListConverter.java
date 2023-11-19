package com.ndkmusic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;

@Component
public class PlayListConverter {

//	@Autowired
//	private SongRepository songRepository;

	@Autowired
	private SongConverter songConverter;

	public PlayList toEntity(PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		playList.setName(playListDTO.getName());
		playList.setThumbnail(playListDTO.getThumbnail());
		playList.setPreface(playList.getPreface());
		return playList;
	}

//	public PlayListDTO toDTO(PlayList playList) {
//		PlayListDTO playListDTO = new PlayListDTO();
//		playListDTO.setId(playList.getId());
//		playListDTO.setName(playList.getName());
////		playListDTO.setFavoriteSong(playList.getFavoriteSong());
//		playListDTO.setModifiedDate(playList.getModifiedDate());
//		playListDTO.setModifiedBy(playList.getModifiedBy());
//		playListDTO.setCreatedBy(playList.getCreatedBy());
//		playListDTO.setCreatedDate(playList.getCreatedDate());
//		playListDTO.setEmailUser(playList.getUser().getEmail());
//		return playListDTO;
//	}

	public PlaylistSong toDTO(PlayList playList) {
		List<SongDTO> songs = new ArrayList<SongDTO>();
		for (Song song : playList.getSongs()) {
			songs.add(songConverter.toDTO(song));
		}
		PlaylistSong dto = new PlaylistSong(songs, playList.getName());
		dto.setId(playList.getId());
		dto.setModifiedDate(playList.getModifiedDate());
		dto.setModifiedBy(playList.getModifiedBy());
		dto.setCreatedBy(playList.getUser().getEmail());
		dto.setCreatedDate(playList.getCreatedDate());
		dto.setThumbnail(playList.getThumbnail());
		dto.setTopic(playList.getTopic().getName());
		dto.setPreface(playList.getPreface());
		return dto;
	}

	public PlayList toEntity(PlayListDTO playListDTO, PlayList playList) {
		playList.setName(playListDTO.getName());
		playList.setModifiedBy(playListDTO.getEmailUser());
		playList.setThumbnail(playListDTO.getThumbnail());
		playList.setPreface(playListDTO.getPreface());
		return playList;
	}
}
