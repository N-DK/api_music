package com.ndkmusic.dto;

import java.util.List;

public class AlbumDTO {
	private String name;
	private String thumbnail;
	private Long totalListen;
	private String genresCode;
	private List<String> artists;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Long getTotalListen() {
		return totalListen;
	}

	public void setTotalListen(Long totalListen) {
		this.totalListen = totalListen;
	}

	public String getGenresCode() {
		return genresCode;
	}

	public void setGenresCode(String genresCode) {
		this.genresCode = genresCode;
	}

	public List<String> getArtists() {
		return artists;
	}

	public void setArtists(List<String> artists) {
		this.artists = artists;
	}

}
