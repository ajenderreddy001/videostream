package com.example.videostream.model;

import com.example.videostream.serilizer.CategorySetSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
public class Media extends Audit implements Comparable<Media> {
    private static final String MEDIA_DIRECTORY="videos";

    @Id
    @Column(name = "media_id")
    @GeneratedValue()
    private int mediaId;

    @Column(nullable = false)
    private String mediaName;

    @Column(nullable = false)
    private String mediaPath;

    @Column(nullable = false)
    private String mediaType;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="category_and_media",joinColumns=@JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    @JsonSerialize(using = CategorySetSerializer.class)
    private Set<Category> mediaCategory;

    public Media(){

    }

    public static String getMediaDirectory() {
        return MEDIA_DIRECTORY;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Set<Category> getMediaCategory() {
        return mediaCategory;
    }

    public void setMediaCategory(Set<Category> mediaCategory) {
        this.mediaCategory = mediaCategory;
    }

    @Override
    public String toString() {
        return "id:"+mediaId+" name"+mediaName;
    }


    @Override
    public int compareTo(Media o) {
        return this.mediaId-o.mediaId;
    }
}
