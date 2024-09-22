package com.FileUpload.DealWithImages.Repository;

import com.FileUpload.DealWithImages.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData , Long> {
    Optional<ImageData> findByName(String name);
}
