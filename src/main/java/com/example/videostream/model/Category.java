package com.example.videostream.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category implements Comparable<Category>{
    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Column(nullable = false)
    private String categoryName;

    @ManyToMany()
    @Column(name = "category_media")
    @JoinTable(name="category_and_media",joinColumns=@JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="media_id"))
    private Set<Media> categoryMedia;

    public Category(){

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Media> getCategoryMedia() {
        return categoryMedia;
    }

    public void setCategoryMedia(Set<Media> categoryMedia) {
        this.categoryMedia = categoryMedia;
    }

    @Override
    public String toString() {
        return "id:"+categoryId+" name:"+categoryName;
    }

    @Override
    public int compareTo(Category o) {
        return  this.categoryId-o.categoryId;
    }
}
