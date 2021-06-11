package monkhub.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import monkhub.file.ImageCompression;
import monkhub.file.ImageMultipart;
import monkhub.file.ImageUrl;

@Controller
public class ImageUpload {

    private final ImageCompression imageCompression;
    
    

    @Autowired
    public ImageUpload(ImageCompression imageCompression) {
        this.imageCompression = imageCompression;
    }

    @PostMapping(value = "image/upload/file/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void uploadJpgImageFile(
            @PathVariable("name") String name,
            @RequestPart(value = "file") MultipartFile multipartFile) {
        imageCompression.compress(new ImageMultipart(multipartFile), name);
    }
    
    

    @PostMapping(value = "image/upload/url/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void uploadJpgImageUrl(
            @PathVariable("name") String name,
            @RequestBody String urlAsString) {
        URL url;

        try {
            url = new URL(urlAsString);
            url.toURI();
        } catch (MalformedURLException| URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        imageCompression.compress(new ImageUrl(url), name);
    }
}
