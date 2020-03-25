package com.serenium.demo.controller;

import com.cloudinary.utils.ObjectUtils;
import com.serenium.demo.model.Image;
import com.serenium.demo.repository.ImageRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.cloudinary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    private ImageRepository imageRepository;


    @Autowired
    public ImageUploader(ImageRepository imageRepository) {
        this.imageRepository=imageRepository;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dked1kdf3",
                "api_key", "461637265557737",
                "api_secret", "oOZ5445Fz5ssUS1uBsUPCuzEAZs"));
    }

    public String uploadFile(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepository.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult.get("url").toString();
    }

    public static void main(String[] args) {

    }


}
