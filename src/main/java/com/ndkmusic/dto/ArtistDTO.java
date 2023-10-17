package com.ndkmusic.dto;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArtistDTO extends AbstractDTO<ArtistDTO>{
	private String artistName;
	private Long numberFollower;
	private int gender;
	private Date birthday;
	private String biography;
	private String placeOfBirth;
	private String profilePath;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Long getNumberFollower() {
		return numberFollower;
	}

	public void setNumberFollower(Long numberFollower) {
		this.numberFollower = numberFollower;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
			this.birthday = (Date) dateFormat.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

}
