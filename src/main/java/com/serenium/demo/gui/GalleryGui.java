package com.serenium.demo.gui;

import com.serenium.demo.model.Image;
import com.serenium.demo.repository.ImageRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {
    private final ImageRepository imageRepository;

    @Autowired
    public GalleryGui(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        Label label = new Label();
        label.setText("Gallery");
        List<Image> images = imageRepository.findAll();

        images.stream().forEach(element ->{
            com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(element.getAdress(),"none");
            add(image);
        } );



    }


}
