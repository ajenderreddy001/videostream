package com.example.videostream.repository;

import com.example.videostream.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Integer>{
}
