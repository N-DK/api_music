package com.ndkmusic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "song")
public class Song extends BaseEntity {
	@Column
	private String title;

	@Column
	@Lob
	private String lyric;

	@Column(name = "audio_url")
	private String audioUrl;

	@Column
	private String thumbnail;

	@Column(name = "time_play")
	private String timePlay;

	@Column
	private Long totalListen;

//	cascade = CascadeType.ALL để tự động cập nhật bảng trung gian
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "song_album", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "album_id"))
	private List<Album> albums = new ArrayList<Album>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "song_artist", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> songArtists = new ArrayList<Artist>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "song_play_list", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "play_list_id"))
	private List<PlayList> playLists = new ArrayList<PlayList>();

	@ManyToOne
	@JoinColumn(name = "genres_id")
	private Genres genresSong;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
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

	public Genres getGenres() {
		return genresSong;
	}

	public void setGenres(Genres genres) {
		this.genresSong = genres;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Artist> getSongArtists() {
		return songArtists;
	}

	public void setSongArtists(List<Artist> songArtists) {
		this.songArtists = songArtists;
	}

	public List<PlayList> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(List<PlayList> playLists) {
		this.playLists = playLists;
	}

}
