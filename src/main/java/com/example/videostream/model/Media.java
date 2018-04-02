package com.example.videostream.model;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Set;

@Entity
public class Media implements Comparable<Media> {
    private static final String MEDIA_DIRECTORY="videos";

    @Id
    @Column(name = "media_id")
    private int mediaId;

    @Column(nullable = false)
    private String mediaName;

    @Column(nullable = false)
    private String mediaPath;

    @Column(nullable = false)
    private String mediaType;

    @Column(nullable = false)
    private Timestamp uploadedTimeStamp;

    @ManyToMany(mappedBy = "categoryMedia")
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

    public Timestamp getUploadedTimeStamp() {
        return uploadedTimeStamp;
    }

    public void setUploadedTimeStamp(Timestamp uploadedTimeStamp) {
        this.uploadedTimeStamp = uploadedTimeStamp;
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
