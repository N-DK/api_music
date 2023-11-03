package com.ndkmusic.dto;

import java.util.List;

public class PlaylistSong extends AbstractDTO<PlayListDTO> {
	private List<SongDTO> songs;
	private String name;

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

}
