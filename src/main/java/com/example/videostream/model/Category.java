package com.example.videostream.model;

import com.example.videostream.serilizer.MediaSetSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category extends Audit implements Comparable<Category>{
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private int categoryId;

    @Column(nullable = false)
    private String categoryName;

    //@JsonIgnoreProperties({"mediaId","mediaPath","createdDate","lastModifiedDate"})
    @ManyToMany(mappedBy = "mediaCategory")
    @JsonSerialize(using = MediaSetSerializer.class)
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
        return "categoryId:"+ categoryId +" name:"+categoryName;
    }

    @Override
    public int compareTo(Category o) {
        return  this.categoryId -o.categoryId;
    }
}
