package com.ndkmusic.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class UploadToCloud {
	private final static Cloudinary CLOUDINARY = new Cloudinary(ObjectUtils.asMap("cloud_name", "dmvyx3gwr", "api_key",
			"246617192776124", "api_secret", "FtLLhXVZcZuOB0OwHZM2BMXqh3Q", "secure", true));

//	resource_type is the type of file to upload. Valid values: image, raw, video and auto to automatically detect the file type.
	public static String createLinkFromCloud(String originalUrl, String resource_type, String public_id) {
		System.out.println(originalUrl);
		Cloudinary cloudinary = CLOUDINARY;

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

	public static String createSecureLinkFromBlobURL(String base64Image, String resource_type, String public_id) {
		Cloudinary cloudinary = CLOUDINARY;
		try {

			// Giải mã chuỗi base64 thành mảng byte
			byte[] imageBytes = Base64.getDecoder().decode(base64Image);

			// Lưu mảng byte thành file ảnh tạm thời
			String imageName = "temp_image.png"; // Tên file ảnh tạm thời
			FileOutputStream outputStream = new FileOutputStream(imageName);
			outputStream.write(imageBytes);
			outputStream.close();

			String secureUrl = (String) cloudinary.uploader()
					.upload(imageName, ObjectUtils.asMap("resource_type", resource_type, "public_id", public_id))
					.get("secure_url");
			File file = new File(imageName);
			file.delete();

			return secureUrl;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
