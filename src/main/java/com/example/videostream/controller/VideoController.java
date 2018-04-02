package com.example.videostream.controller;

import com.example.videostream.model.Video;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Controller
public class VideoController {

    @GetMapping(value = "/")
    public String home()
    {
        return "home";
    }
    @GetMapping(value = "/uploadvideo")
    public String videoUploadForm()
    {
        return "videouploadform";
    }
    @PostMapping(value = "/uploadvideo")
    public String uploadVideo(@RequestParam("videofile") MultipartFile videoFile, @RequestParam("category") String category)
    {
        if(videoFile==null|| category==null)
            return null;
        String orgFileName=videoFile.getOriginalFilename();
        System.out.println("orginal file name:"+orgFileName);
        List<String> categoryList = new ArrayList<>(Arrays.asList(category.split(",")));
        try{
            byte[] videobytes=videoFile.getBytes();
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream("videos/"+orgFileName));
            bout.write(videobytes);
            bout.flush();
            bout.close();
        }
        catch (Exception e)
        {
            //handle filewrite failure;
        }
        if(VideoRest.catVideos.containsKey(category))
        {
            List<String> list= VideoRest.catVideos.get(category);
            list.add(orgFileName);
        }
        else
        {
            List<String> list=new ArrayList<>();
            list.add(orgFileName);
            VideoRest.catVideos.put(category,list);
        }
        Video video=new Video(orgFileName,categoryList,"/streamvideo/"+orgFileName);
        VideoRest.videoList.add(video);
        VideoRest.storeAllVideos();
        Map<String, List<String>> temp = VideoRest.catVideos;
        Properties properties = new Properties();

        for (Map.Entry<String, List<String>> entry : temp.entrySet()) {
            String key=entry.getKey();
            List<String> value=entry.getValue();
            String json=new Gson().toJson(value);
            properties.put(key,json);
            System.out.println(key+" "+json);
        }
        try {
            properties.store(new FileOutputStream("videos/categories"), null);
        } catch (Exception e) {
            //handle filenotfound exception
            System.out.println("Error in category storing"+ e.getMessage());
        }
        return "video_upload_success";
    }
}
