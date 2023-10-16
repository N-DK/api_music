package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.Role;

public interface RoleReposity extends JpaRepository<Role, Long> {
	Role findOneByCode(String code);
}
