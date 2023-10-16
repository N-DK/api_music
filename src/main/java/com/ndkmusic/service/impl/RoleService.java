package com.ndkmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndkmusic.converter.RoleConverter;
import com.ndkmusic.dto.RoleDTO;
import com.ndkmusic.entities.Role;
import com.ndkmusic.repository.RoleReposity;
import com.ndkmusic.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleReposity roleReposity;

	@Autowired
	private RoleConverter roleConverter;

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		Role role = roleConverter.toEntity(roleDTO);
		roleReposity.save(role);
		return roleConverter.toDTO(role);
	}

}
