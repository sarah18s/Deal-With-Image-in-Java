package com.FileUpload.DealWithImages.Services;

import com.FileUpload.DealWithImages.Entity.FileData;
import com.FileUpload.DealWithImages.Entity.ImageData;
import com.FileUpload.DealWithImages.Repository.*;
import com.FileUpload.DealWithImages.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService implements IStorageServices{

    @Autowired
    private ImageDataRepository imageDataRepository;
    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "C:\\Users\\Lenovo\\Downloads\\DealWithImages\\images\\";

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());


        if(imageData != null){
            return "file Uploaded successfully : " + file.getOriginalFilename();
        }
        return  null;
    }
    public  byte[] downloadImage(String fileName)
    {
        Optional<ImageData> dbImageData = imageDataRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return  images;
    }

    public  String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath =FOLDER_PATH+file.getOriginalFilename();

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());
        file.transferTo(new File(filePath));

        if(fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadedImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
