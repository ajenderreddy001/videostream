package com.example.videostream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@Controller
public class VideoController {

    @GetMapping(value = "/")
    public String home()
    {
        return "home";
    }
}
