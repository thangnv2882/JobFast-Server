package com.thangnv2882.jobfastserver.application.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtil {

  private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

  public static final Path RESOURCES_PATH = CURRENT_FOLDER.resolve(Paths.get("src/main/resources"));

  public static String saveFile(String newFileName, String uploadPath, MultipartFile multipartFile) throws IOException {
    Path path = RESOURCES_PATH.resolve(Paths.get(uploadPath));
    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    String fileType = fileName.substring(fileName.lastIndexOf("."));
    String newFile = newFileName + fileType;
    Path filePath;
    try (InputStream inputStream = multipartFile.getInputStream()) {
      filePath = path.resolve(newFile);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Could not save file: " + fileName);
    }
    return newFile;
  }
}
