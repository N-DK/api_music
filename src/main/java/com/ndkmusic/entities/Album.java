package com.ndkmusic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//import org.springframework.data.annotation.Id;
//
//@SuppressWarnings("unused")
@Entity
@Table(name = "album")
@JsonPropertyOrder
public class Album extends BaseEntity {

	@Column
	private String name;

	@Column
	@Lob
	private String thumbnail;

	@Column(name = "total_listen")
	private Long totalListen;

	@ManyToMany
	@JoinTable(name = "song_album", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> songs = new ArrayList<Song>();

	@ManyToMany
	@JoinTable(name = "album_artist", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> albumArtists = new ArrayList<Artist>();
	
	@ManyToMany(mappedBy = "albums")
	private List<User> user = new ArrayList<User>();

	@ManyToOne
	@JoinColumn(name = "genres_id")
	private Genres genresAlbum;

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

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Artist> getAlbumArtists() {
		return albumArtists;
	}

	public void setAlbumArtists(List<Artist> albumArtists) {
		this.albumArtists = albumArtists;
	}

	public Genres getGenres() {
		return genresAlbum;
	}

	public void setGenres(Genres genresAlbum) {
		this.genresAlbum = genresAlbum;
	}

}
