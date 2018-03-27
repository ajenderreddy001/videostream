package com.example.videostream.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
        if(Video.catVideos.containsKey(category))
        {
            List<String> list=Video.catVideos.get(category);
            list.add(orgFileName);
        }
        else
        {
            List<String> list=new ArrayList<>();
            list.add(orgFileName);
            Video.catVideos.put(category,list);
        }
        Map<String, List<String>> temp = Video.catVideos;
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
