package com.ndkmusic.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist extends BaseEntity {

	@Column
	private String name;

	@Column(name = "number_follow")
	private Long numberFollow;

	@Column
	private int gender;

	@Column
	private Date birthday;

	@Column
	@Lob
	private String biography;

	@Column(name = "profile_path")
	private String profilePath;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@ManyToMany(mappedBy = "songArtists")
	private List<Song> songs = new ArrayList<Song>();

	@ManyToMany(mappedBy = "albumArtists")
	private List<Album> albums = new ArrayList<Album>();

	@ManyToMany(mappedBy = "arists")
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumberFollow() {
		return numberFollow;
	}

	public void setNumberFollow(Long numberFollow) {
		this.numberFollow = numberFollow;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

}
