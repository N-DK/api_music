package com.ndkmusic.service;

import com.ndkmusic.api.output.UserOutput;
import com.ndkmusic.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);

	UserOutput delete(Long id, Long typeId, String type);
}
