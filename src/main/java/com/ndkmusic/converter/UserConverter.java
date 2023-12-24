package com.ndkmusic.converter;

import static com.ndkmusic.utils.UploadToCloud.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ndkmusic.api.output.UserOutput;
import com.ndkmusic.dto.AlbumDTO;
import com.ndkmusic.dto.PlaylistSong;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.dto.UserDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.PlayList;
import com.ndkmusic.entities.Song;
import com.ndkmusic.entities.User;

@Component
public class UserConverter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SongConverter songConverter;

	@Autowired
	private PlayListConverter playListConverter;

	@Autowired
	private AlbumConverter albumConverter;

	public User toEntity(UserDTO userDTO) {
		userDTO.setAvatar(
				createLinkFromCloud(userDTO.getAvatar(), "video", "ndk_music/avatar/" + userDTO.getNickName()));
		User user = new User();
		user.setNickName(userDTO.getNickName());
		user.setBirthday(userDTO.getBirthday());
		user.setAvatar(userDTO.getAvatar());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPasswrord()));
		return user;
	}

	public UserDTO toDTO(User user) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		UserDTO userDTO = new UserDTO();
		userDTO.setNickName(user.getNickName());
		userDTO.setCreatedBy(user.getCreatedBy());
		userDTO.setModifiedBy(user.getModifiedBy());
		userDTO.setModifiedDate(user.getModifiedDate());
		userDTO.setBirthday(String.format("%s", dateFormat.format(user.getBirthday())));
		userDTO.setAvatar(user.getAvatar());
		userDTO.setEmail(user.getEmail());
		userDTO.setPasswrord(user.getPassword());
		userDTO.setId(user.getId());
		userDTO.setCreatedDate(user.getCreatedDate());

		for (Song song : user.getSongs()) {
			userDTO.getSongs().add(song.getId());
		}
		for (PlayList playList : user.getPlayLists()) {
			userDTO.getPlaylist().add(playList.getId());
		}
		return userDTO;
	}

	public UserOutput toOutput(User user) {
		UserOutput userOutput = new UserOutput();
		List<SongDTO> songs = new ArrayList<SongDTO>();
		List<PlaylistSong> playlists = new ArrayList<PlaylistSong>();
		List<AlbumDTO> albums = new ArrayList<AlbumDTO>();
		List<Long> artistIds = new ArrayList<Long>();
		for (Song song : user.getSongs()) {
			songs.add(songConverter.toDTO(song));
		}
		for (PlayList playList : user.getUserPlayLists()) {
			playlists.add(playListConverter.toDTO(playList));
		}
		for (PlayList playList : user.getPlayLists()) {
			playlists.add(playListConverter.toDTO(playList));
		}
		for (Album album : user.getAlbums()) {
			albums.add(albumConverter.toDTO(album));
		}
		for (Artist artist : user.getArists()) {
			artistIds.add(artist.getId());
		}
		userOutput.setId(user.getId());
		userOutput.setEmail(user.getEmail());
		userOutput.setAvatar(user.getAvatar());
		userOutput.setNickName(user.getNickName());
		userOutput.setRoleCode(user.getRole().getCode());
		userOutput.setSongs(songs);
		userOutput.setPlaylist(playlists);
		userOutput.setAlbums(albums);
		userOutput.setArtistIds(artistIds);
		return userOutput;
	}

	public User toEntity(UserDTO userDTO, User user) {
		if (userDTO.getNickName() != null) {
			user.setNickName(userDTO.getNickName());
		}
		if (userDTO.getBirthday() != null) {
			user.setBirthday(userDTO.getBirthday());
		}
		if (userDTO.getAvatar() != null) {
			userDTO.setAvatar(createSecureLinkFromBlobURL(userDTO.getAvatar(), "image",
					"ndk_music/avatar/" + user.getNickName()));
			user.setAvatar(userDTO.getAvatar());
		}
		if (userDTO.getEmail() != null) {
			user.setEmail(userDTO.getEmail());
		}
		return user;
	}
}
