package com.ndkmusic.dto;

import java.util.List;

public class SongDTO extends AbstractDTO<SongDTO> {
	private String title;
	private String audioUrl;
	private String thumbnail;
	private String lyric;
	private String timePlay;
	private Long totalListen;
	private String genresCode;
	private List<Object> artists;
	private List<Object> albums;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTimePlay() {
		return timePlay;
	}

	public void setTimePlay(String timePlay) {
		this.timePlay = timePlay;
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

	public List<Object> getArtists() {
		return artists;
	}

	public void setArtists(List<Object> artists) {
		this.artists = artists;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public List<Object> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Object> albums) {
		this.albums = albums;
	}

}
