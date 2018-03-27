package com.example.videostream.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.*;

@RestController
public class Video {
     static HashMap<String,List<String>> catVideos;
    static
    {
        catVideos=new HashMap<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("videos/categories"));
        }
        catch (Exception e)
        {
            //handle exception
            System.out.println("Unable to load properties file"+e.getMessage());
        }
        for (String key : properties.stringPropertyNames())
        {
            String value=(String) properties.get(key);
            System.out.println(key+" "+value);
            ArrayList<String>  arrayList= new Gson().fromJson(value, new TypeToken<List<String>>(){}.getType());
            catVideos.put(key,arrayList);
        }
        System.out.println("Category LOADED");
    }
    @GetMapping(value = "/streamvideo/{name}")
    public StreamingResponseBody stream(@PathVariable String name) throws FileNotFoundException {
        File videoFile = new File("videos/" + name);
        final InputStream videoFileStream = new FileInputStream(videoFile);
        return (os) -> {
            readAndWrite(videoFileStream, os);
        };
    }

    private void readAndWrite(final InputStream is, OutputStream os)
            throws IOException {
        byte[] data = new byte[2048];
        int read = 0;
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
        }
        os.flush();
    }
    @GetMapping(value="/all/catvideos")
    public String getAllCatList()
    {
        String jsonAllCatList = new Gson().toJson(catVideos);
        return jsonAllCatList;
    }
    /*
    @GetMapping(value = '/video/{name}')
    public String getvideocats()
    {
        String jsonAllCatList=new Gson().toJson(catVideos);
        return null;

    }*/

    protected void finalize() {
        Map<String, List<String>> temp = catVideos;
        Properties properties = new Properties();

        for (Map.Entry<String, List<String>> entry : temp.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        try {
            properties.store(new FileOutputStream("videos/categories"), null);
        } catch (Exception e) {
            //handle filenotfound exception
        }
    }
}