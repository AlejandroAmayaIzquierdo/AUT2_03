package com.example.aut2_03.db;

import android.graphics.Bitmap;

public class Game {
    private String name;
    private String developer;

    private byte[] image;
    public Game() {
    }
    public Game(String name, String developer) {
        this.name = name;
        this.developer = developer;
    }
    public Game(String name, String developer, byte[] image) {
        this.name = name;
        this.developer = developer;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public byte[] getImage() {return image;}

    public void setImage(byte[] image) {this.image = image;}
}
