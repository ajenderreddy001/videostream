package com.example.videostream.controller;

import com.example.videostream.model.Video;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class VideoRest {
    static List<Video> videoList;
     static HashMap<String,List<String>> catVideos;
    static
    {
        loadAllVideos();
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
    @GetMapping(value = "/streamvideo/{name}",produces = "video/mp4")
    @ResponseBody()
    public StreamingResponseBody stream(@PathVariable String name) throws FileNotFoundException {
        try {
            File videoFile = new File("videos/" + name);
            final InputStream videoFileStream = new FileInputStream(videoFile);
            return (OutputStream os) -> {
                readAndWrite(videoFileStream, os);
            };
        }
        catch (Exception e)
        {
            System.out.println("VideoNot Found with filename as "+name);
            return null;
        }


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
    @GetMapping(value="/all/cats",produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String getAllCatList()
    {
        //converts catlist to json
        return new Gson().toJson(catVideos);
    }

    @GetMapping(value = "/all/video", produces = "application/json; charset=utf-8")
    @ResponseBody()
    public String getAllVideos()
    { return new Gson().toJson(videoList); }

    private static void loadAllVideos()
    {
        videoList=new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get("videos/videolist")));
            JsonElement jsonElement=new JsonParser().parse(content);
            JsonArray jsonArray=jsonElement.getAsJsonArray();
            videoList=new Gson().fromJson(jsonElement,new TypeToken<ArrayList<Video>>() {}.getType());
        }
        catch (Exception e){
            System.out.println("Error in loading videolist "+e.getMessage());
        }
        String videoliststring=new Gson().toJson(videoList);
        System.out.println(videoliststring);
    }
    public static void storeAllVideos()
    {
        String json = new Gson().toJson(videoList);
        try{
            PrintWriter out = new PrintWriter("videos/videolist");
            out.write(json);
            out.flush();
            out.close();
        }
        catch (Exception e){
            System.out.println("Error in storing videolist"+e.getMessage());
        }
    }
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