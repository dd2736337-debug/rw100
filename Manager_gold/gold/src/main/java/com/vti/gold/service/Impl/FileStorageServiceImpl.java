package com.vti.gold.service.Impl;


import com.vti.gold.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileStorageServiceImpl implements FileStorageService {


    @Value("${app.file.upload-dir}")
    private String uploadDir;


    @Override
    public String storeFile(MultipartFile file) {


        if (file == null || file.isEmpty()) {

            return null;

        }


        try {


            Path uploadPath =
                    Paths.get(uploadDir)
                            .toAbsolutePath()
                            .normalize();


            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);

            }


            String extension = "";


            String original =
                    file.getOriginalFilename();


            if (original != null
                    && original.contains(".")) {


                extension =
                        original.substring(
                                original.lastIndexOf(".")
                        );

            }


            String fileName =
                    UUID.randomUUID()
                            + extension;


            Path target =
                    uploadPath.resolve(fileName);


            Files.copy(

                    file.getInputStream(),

                    target,

                    StandardCopyOption.REPLACE_EXISTING

            );


            return "/uploads/" + fileName;


        } catch (IOException e) {


            throw new RuntimeException(
                    "Lỗi lưu file"
            );


        }

    }

}