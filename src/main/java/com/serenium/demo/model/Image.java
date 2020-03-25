package com.serenium.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adress;

    public Image() {
    }

    public Image( String adress) {
        this.adress=adress;
    }

    public String getAdress() {
        return adress;
    }
}
