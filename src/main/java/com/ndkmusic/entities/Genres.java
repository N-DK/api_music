package com.ndkmusic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genres extends BaseEntity {

	@Column
	private String name;

	@Column
	private String code;

	@OneToMany(mappedBy = "genresAlbum")
	private List<Album> ablums = new ArrayList<Album>();

	@OneToMany(mappedBy = "genresSong")
	private List<Song> songs = new ArrayList<Song>();

	public String getName() {
		return name;
	}

	public List<Album> getAblums() {
		return ablums;
	}

	public void setAblums(List<Album> ablums) {
		this.ablums = ablums;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
