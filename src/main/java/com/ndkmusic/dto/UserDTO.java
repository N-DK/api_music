package com.ndkmusic.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDTO {
	private String nickName;
	private String email;
	private Date birthday;
	private String passwrord;
	private String avatar;
	private String roleCode;

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

}
