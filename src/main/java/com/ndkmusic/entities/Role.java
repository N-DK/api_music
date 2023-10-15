package com.ndkmusic.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private List<User> users = new ArrayList<User>();

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
