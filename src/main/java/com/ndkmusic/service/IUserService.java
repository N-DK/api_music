package com.ndkmusic.service;

import com.ndkmusic.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);

	void delete(Long id, Long typeId, String type);
}
