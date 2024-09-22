package com.FileUpload.DealWithImages.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "file_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;
}

/*
@Builder:

This annotation enables the Builder pattern for the class,
allowing you to create instances of the class in a more readable and flexible way.
It generates a static inner class with methods for setting each property.
Usage: FileData fileData = FileData.builder().name("").type("....").filePath("....").build();
*/
