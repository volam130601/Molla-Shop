package com.molla.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void removeFile(String uploadDir, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir + "/" + fileName);
        if (Files.exists(uploadPath)) {
            Files.delete(uploadPath);
        } else System.out.println("File cannot found.");
    }

    public static void removeFileAll(String uploadDir) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (Files.exists(uploadPath)) {
            FileUtils.deleteDirectory(uploadPath.toFile());
        } else System.out.println("File cannot found.");
    }
}
