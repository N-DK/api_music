package com.ndkmusic.dto;

import java.util.List;

public class PlaylistSong extends AbstractDTO<PlayListDTO> {
	private List<SongDTO> songs;
	private String name;
	private String thumbnail;
	private String topic;
	private String subTitle;
	private String preface;
	private List<ArtistDTO> artists;

	public PlaylistSong(List<SongDTO> songs, String name) {
		super();
		this.songs = songs;
		this.name = name;
	}

	public List<SongDTO> getSongs() {
		return songs;
	}

	public void setSongs(List<SongDTO> songs) {
		this.songs = songs;
	}

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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<ArtistDTO> getArtists() {
		return artists;
	}

	public void setArtists(List<ArtistDTO> artists) {
		this.artists = artists;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

}
