package com.example.videostream.repository;

import com.example.videostream.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaRepository extends JpaRepository<Media,Integer>{

    public Media findByMediaId(int mediaId);

    @Modifying
    @Query("Update Media m SET m.mediaName=:mediaName WHERE m.mediaId=:mediaId")
    public void updateMedia(@Param("mediaId") int mediaId, @Param("mediaName") String mediaName);
}
