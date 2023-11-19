package com.ndkmusic.dto;

import java.util.List;

public class PlaylistSong extends AbstractDTO<PlayListDTO> {
	private List<SongDTO> songs;
	private String name;
	private String thumbnail;
	private String topic;
	private String preface;

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

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
