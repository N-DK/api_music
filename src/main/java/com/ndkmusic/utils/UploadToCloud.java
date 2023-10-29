package com.ndkmusic.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class UploadToCloud {
//	resource_type is the type of file to upload. Valid values: image, raw, video and auto to automatically detect the file type.
	public static String createLinkFromCloud(String originalUrl, String resource_type, String public_id) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dmvyx3gwr", "api_key",
				"246617192776124", "api_secret", "FtLLhXVZcZuOB0OwHZM2BMXqh3Q", "secure", true));

		try {
			// Tạo đối tượng URL từ đường dẫn URL
			URL url = new URL(originalUrl);

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
				String secureUrl = (String) cloudinary.uploader()
						.upload(fileData, ObjectUtils.asMap("resource_type", resource_type, "public_id", public_id))
						.get("secure_url");
				// Sử dụng URL mới
				return secureUrl;
			} else {
				System.out.println("Lỗi tải lên tệp tin từ URL");
			}
			// Đóng kết nối
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
