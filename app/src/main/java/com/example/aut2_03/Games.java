package com.example.aut2_03;

public class Games {
    private String name;
    private String developer;
    public Games() {
    }
    public Games(String name, String developer) {
        this.name = name;
        this.developer = developer;
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
}
