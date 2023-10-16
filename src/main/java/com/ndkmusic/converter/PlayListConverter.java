package com.ndkmusic.converter;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.PlayListDTO;
import com.ndkmusic.entities.PlayList;

@Component
public class PlayListConverter {

	public PlayList toEntity(PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		playList.setName(playListDTO.getName());
		playList.setFavoriteSong(playListDTO.getFavoriteSong());
		return playList;
	}
	
	public PlayListDTO toDTO(PlayList playList) {
		PlayListDTO playListDTO = new PlayListDTO();
		playListDTO.setName(playList.getName());
		playListDTO.setFavoriteSong(playList.getFavoriteSong());
		return playListDTO;
	} 
	
	public PlayList toEntity(PlayListDTO playListDTO, PlayList playList) {
		playList.setName(playListDTO.getName());
		playList.setFavoriteSong(playListDTO.getFavoriteSong());
		return playList;
	}
}