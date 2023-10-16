package com.ndkmusic.converter;

import org.springframework.stereotype.Component;

import com.ndkmusic.dto.RoleDTO;
import com.ndkmusic.entities.Role;

@Component
public class RoleConverter {
	public Role toEntity(RoleDTO roleDTO) {
		Role role = new Role();
		role.setName(roleDTO.getName());
		role.setCode(roleDTO.getCode());
		return role;
	}

	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName(role.getName());
		roleDTO.setCode(role.getCode());
		return roleDTO;
	}
}
