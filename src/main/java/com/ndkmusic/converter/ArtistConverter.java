package com.ndkmusic.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.ArtistDTO;
import com.ndkmusic.entities.Artist;

@Component
public class ArtistConverter {
	public Artist toEntity(ArtistDTO artistDTO) {
		Artist artist = new Artist();
		artist.setName(artistDTO.getArtistName());
		artist.setBirthday(artistDTO.getBirthday());
		artist.setGender(artistDTO.getGender());
		artist.setProfilePath(artistDTO.getProfilePath());
		artist.setBiography(artistDTO.getBiography());
		artist.setNumberFollow((artistDTO.getNumberFollower() != null ? artistDTO.getNumberFollower() : 0));
		artist.setPlaceOfBirth(artistDTO.getPlaceOfBirth());
		return artist;
	}

	public ArtistDTO toDTO(ArtistDTO artist) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setArtistName(artist.getArtistName());
		artistDTO.setBirthday(String.format("%s", dateFormat.format(artist.getBirthday())));
		artistDTO.setGender(artist.getGender());
		artistDTO.setProfilePath(artist.getProfilePath());
		artistDTO.setBiography(artist.getBiography());
		artistDTO.setNumberFollower(artist.getNumberFollower());
		artistDTO.setPlaceOfBirth(artist.getPlaceOfBirth());
		return artistDTO;
	}
}
