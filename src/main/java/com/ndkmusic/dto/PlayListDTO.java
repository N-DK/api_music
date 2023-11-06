package com.ndkmusic.dto;

public class PlayListDTO extends AbstractDTO<PlayListDTO>{
	private String name;
	private Object[] favoriteSong;
	private String emailUser;
	private String thumbnail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object[] getFavoriteSong() {
		return favoriteSong;
	}

	public void setFavoriteSong(Object[] favoriteSong) {
		this.favoriteSong = favoriteSong;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}
