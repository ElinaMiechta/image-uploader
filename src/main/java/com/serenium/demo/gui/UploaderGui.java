package com.serenium.demo.gui;

import com.serenium.demo.controller.ImageUploader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;



@Route("upload")
public class UploaderGui extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploaderGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(clickEvent ->
        {

            String uploadedImage = imageUploader.uploadFile(textField.getValue());
            Image image = new Image(uploadedImage, "image not found :(");
            label.setText("Image uploaded!");
            add(label);
            add(image);

        });

        add(textField);
        add(button);

    }
}
