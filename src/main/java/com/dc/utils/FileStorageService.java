package com.dc.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

public class FileStorageService {
    public static String storeFile(MultipartFile file,String mediaType) throws IOException {


        if(file.isEmpty())
            throw new RuntimeException("File is Empty");

        String fileName = file.getOriginalFilename();
        String fileExtension = "";

        if(fileName != null && fileName.contains("."))
            fileExtension = fileName.substring(fileName.lastIndexOf("."));

        String fileUniqueName = UUID.randomUUID()+fileExtension;
        String mediaPath = "media/"+ (Objects.equals(mediaType, "img") ? "images" : "PDFs");
        Path filePath = Paths.get(mediaPath).toAbsolutePath().normalize();

        Path targetLocation = filePath.resolve(filePath+"/"+fileUniqueName);

        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return mediaPath+"/"+fileUniqueName;
    }

}