package com.example.vejalivros.model;

import java.io.Serializable;

public final class Book implements Serializable {
    private int id;
    private String author;
    private String name;
    private String text;

    public Book(int id, String author, String name, String text) {
        this.id = id;
        this.author =  author;
        this.name = name;
        this.text = text;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
