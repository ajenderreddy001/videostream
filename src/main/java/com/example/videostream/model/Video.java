package com.example.videostream.model;


import java.util.List;

public class Video {
    private String fileName;
    private List<String> categories;
    private String url;
    public Video(){}

    public Video(String fileName, List<String> categories, String url) {
        this.fileName = fileName;
        this.categories = categories;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
