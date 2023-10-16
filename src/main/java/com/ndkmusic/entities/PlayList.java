package com.ndkmusic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "play_list")
public class PlayList extends BaseEntity {
	@Column
	private String name;

	@Column(name = "favorite_song")
	private String favoriteSong;

	@ManyToMany(mappedBy = "playLists", cascade = CascadeType.ALL)
	private List<Song> songs = new ArrayList<Song>();

	@ManyToOne
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFavoriteSong() {
		return favoriteSong;
	}

	public void setFavoriteSong(String favoriteSong) {
		this.favoriteSong = favoriteSong;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
