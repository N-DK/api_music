package com.ndkmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndkmusic.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByEmail(String email);

	User findOneById(Long id);
}
