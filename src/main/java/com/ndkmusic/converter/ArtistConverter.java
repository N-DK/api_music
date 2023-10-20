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

	public ArtistDTO toDTO(Artist artist) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setId(artist.getId());
		artistDTO.setArtistName(artist.getName());
		artistDTO.setBirthday(String.format("%s", dateFormat.format(artist.getBirthday())));
		artistDTO.setGender(artist.getGender());
		artistDTO.setCreatedBy(artist.getCreatedBy());
		artistDTO.setCreatedDate(artist.getCreatedDate());
		artistDTO.setModifiedBy(artist.getModifiedBy());
		artistDTO.setModifiedDate(artist.getModifiedDate());
		artistDTO.setProfilePath(artist.getProfilePath());
		artistDTO.setBiography(artist.getBiography());
		artistDTO.setNumberFollower(artist.getNumberFollow());
		artistDTO.setPlaceOfBirth(artist.getPlaceOfBirth());
		return artistDTO;
	}

}
