package com.ndkmusic.dto;

import java.util.List;

public class SongDTO extends AbstractDTO<SongDTO>{
	private String title;
	private String audioUrl;
	private String thumbnail;
	private String lyric;
	private String timePlay;
	private Integer totalListen;
	private String genresCode;
	private List<String> artists;
	private List<String> albums;
	private List<String> playLists;

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

	public Integer getTotalListen() {
		return totalListen;
	}

	public void setTotalListen(Integer totalListen) {
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

	public List<String> getAlbums() {
		return albums;
	}

	public void setAlbums(List<String> albums) {
		this.albums = albums;
	}

	public List<String> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(List<String> playLists) {
		this.playLists = playLists;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

}
