package com.ndkmusic.dto;

public class PlayListDTO extends AbstractDTO<PlayListDTO> {
	private String name;
	private Object[] favoriteSong;
	private String emailUser;
	private String thumbnail;
	private String topicCode;
	private String subTitle;
	private String preface;

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

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

}
