package com.ndkmusic.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO extends AbstractDTO<UserDTO> {
	private String nickName;
	private String email;
	private Date birthday;
	private String passwrord;
	private String avatar;
	private String roleCode;
	private List<Long> songs = new ArrayList<Long>();
	private List<Long> playlistSongs = new ArrayList<Long>();

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.birthday = (Date) dateFormat.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPasswrord() {
		return passwrord;
	}

	public void setPasswrord(String passwrord) {
		this.passwrord = passwrord;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<Long> getSongs() {
		return songs;
	}

	public void setSongs(List<Long> songs) {
		this.songs = songs;
	}

	public List<Long> getPlaylistSongs() {
		return playlistSongs;
	}

	public void setPlaylistSongs(List<Long> playlistSongs) {
		this.playlistSongs = playlistSongs;
	}

}
