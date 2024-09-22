package com.FileUpload.DealWithImages.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageServices {
    public String uploadImage(MultipartFile file) throws IOException;
    public  byte[] downloadImage(String fileName);
    public  String uploadImageToFileSystem(MultipartFile file)throws IOException;
    public byte[] downloadedImageFromFileSystem(String fileName)throws IOException;
}
