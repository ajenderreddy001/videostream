package com.example.videostream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class Video {
    private static File categories;
    private static HashMap<String,List<String>> ;
    static
    {

    }
    @GetMapping(value = "/video/{name}")
    public StreamingResponseBody stream(@PathVariable String name) throws FileNotFoundException {
        File videoFile = new File("videos/" + name + ".mp4");
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
}