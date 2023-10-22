package com.ndkmusic.converter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ndkmusic.dto.SongAlbumId;
import com.ndkmusic.dto.SongArtistId;
import com.ndkmusic.dto.SongDTO;
import com.ndkmusic.entities.Album;
import com.ndkmusic.entities.Artist;
import com.ndkmusic.entities.Song;

@Component
public class SongConverter {

	public Song toEntity(SongDTO songDTO) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dmvyx3gwr", "api_key",
				"246617192776124", "api_secret", "FtLLhXVZcZuOB0OwHZM2BMXqh3Q", "secure", true));

		try {
			// Tạo đối tượng URL từ đường dẫn URL
			URL url = new URL(songDTO.getAudioUrl());

			// Mở kết nối tới URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Kiểm tra xem kết nối có thành công hay không
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Đọc nội dung từ kết nối
				BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();

				byte[] fileData = outputStream.toByteArray();

				// Tải lên tệp tin từ nội dung đọc được
				String secureUrl = (String) cloudinary.uploader().upload(fileData, ObjectUtils.asMap("resource_type",
						"video", "public_id", "ndk_music/song/" + songDTO.getTitle())).get("secure_url");

				// Sử dụng URL mới
				songDTO.setAudioUrl(secureUrl);
			} else {
				System.out.println("Lỗi tải lên tệp tin từ URL");
			}

			// Đóng kết nối
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Song song = new Song();
		song.setTitle(songDTO.getTitle());
		song.setLyric(songDTO.getLyric());
		song.setAudioUrl(songDTO.getAudioUrl());
		song.setThumbnail(songDTO.getThumbnail());
		song.setTimePlay(songDTO.getTimePlay());
		return song;
	}

	public SongDTO toDTO(Song song) {
		SongDTO songDTO = new SongDTO();
		songDTO.setId(song.getId());
		songDTO.setTitle(song.getTitle());
		songDTO.setLyric(song.getLyric());
		songDTO.setAudioUrl(song.getAudioUrl());
		songDTO.setThumbnail(song.getThumbnail());
		songDTO.setTimePlay(song.getTimePlay());
		songDTO.setModifiedDate(song.getModifiedDate());
		songDTO.setModifiedBy(song.getModifiedBy());
		songDTO.setCreatedBy(song.getCreatedBy());
		songDTO.setCreatedDate(song.getCreatedDate());
		songDTO.setGenresCode(song.getGenres().getCode());
		songDTO.setTotalListen(song.getTotalListen() == null ? 0 : song.getTotalListen());
		List<Object> artists = new ArrayList<Object>();
		List<Object> albums = new ArrayList<Object>();
		for (Album album : song.getAlbums()) {
			albums.add(new SongAlbumId(album.getId(), album.getName()));
		}
		for (Artist artist : song.getSongArtists()) {
			artists.add(new SongArtistId(artist.getId(), artist.getName()));
		}
		songDTO.setArtists(artists);
		songDTO.setAlbums(albums);
		return songDTO;
	}

	public Album toAlbum(Song song) {
		Album album = new Album();
		album.setTotalListen(song.getTotalListen());
		album.setName(song.getTitle() + " (Single)");
		album.setThumbnail(song.getThumbnail());
		album.setGenres(song.getGenres());
		return album;
	}

}
