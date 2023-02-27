package com.example.aut2_03;

public class Game {
    private String name;
    private String developer;

    private Integer image;
    public Game() {
    }
    public Game(String name, String developer) {
        this.name = name;
        this.developer = developer;
    }
    public Game(String name, String developer, Integer image) {
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

    public Integer getImage() {return image;}

    public void setImage(Integer image) {this.image = image;}
}
