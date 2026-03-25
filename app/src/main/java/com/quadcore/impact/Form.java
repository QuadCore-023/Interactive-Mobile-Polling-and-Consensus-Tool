package com.quadcore.impact;

public class Form {
    private final String title;
    private final String description;
    private final String dateCreated;

    public Form(String title, String description, String dateCreated) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDateCreated() { return dateCreated; }
}