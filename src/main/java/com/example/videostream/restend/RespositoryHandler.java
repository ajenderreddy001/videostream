package com.example.videostream.restend;

import com.example.videostream.model.Category;
import com.example.videostream.model.Media;
import com.example.videostream.repository.CategoryRepository;
import com.example.videostream.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class RespositoryHandler {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(value = "/addmedia")
    public boolean addMedia(@Valid @RequestBody Media newmedia){
        try {
            mediaRepository.save(newmedia);
        }
        catch(Throwable throwable)
        {
            System.out.println("newmedia not saved.\n"+ throwable.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping(value = "/addcategory")
    public boolean addCategory(@RequestBody Category category){
        try{
            categoryRepository.save(category);
        }
        catch (Throwable throwable){
            System.out.println("newcategory not saved.\n"+throwable.getMessage());
            return false;
        }
        return true;
    }

    @DeleteMapping(value = "/deletemedia")
    public boolean deleteMedia(@RequestParam int mediaId){
        Media requestedMedia=mediaRepository.findByMediaId(mediaId);
        if(requestedMedia != null){
            mediaRepository.delete(requestedMedia);
            return true;
        }
        else {
            return false;
        }
    }

    @PutMapping(value="/updatemedia")
    public boolean updateMedia(@RequestBody Media toBeUpdateMedia){
        Media m=mediaRepository.findByMediaId(toBeUpdateMedia.getMediaId());
        if(m!=null){
            m.setMediaCategory(toBeUpdateMedia.getMediaCategory());
            m.setMediaName(toBeUpdateMedia.getMediaName());
            m.setMediaPath(toBeUpdateMedia.getMediaPath());
            m.setMediaName(toBeUpdateMedia.getMediaName());
            m.setMediaType(toBeUpdateMedia.getMediaType());
            mediaRepository.save(m);
            return true;
        }
        else{
            return false;
        }
    }
    @GetMapping(value = "/getallmedia")
    public List<Media> getAllMedia(){
        return mediaRepository.findAll();
    }

    @GetMapping(value = "/getallcategory")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @GetMapping(value ="/getallmedianames")
    public List<String> getAllMediaNames(){
        List<Media> media=mediaRepository.findAll();
        //return media.parallelStream().filter(media1 -> media1.getMediaName().contains("aj")).map(Media::getMediaName).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return media.parallelStream()
                .map(Media::getMediaName)
                .filter(s -> s.contains("aj"))
                .sorted(Comparator.reverseOrder())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

    }

}
