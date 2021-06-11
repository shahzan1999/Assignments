package monkhub.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import monkhub.file.ImageMultipart;
import monkhub.service.ImageCompressAWS3Service;


@RestController
public class ImagController {
 
	@Autowired
    private ImageCompressAWS3Service service;
	
	@PostMapping(value="image/compress/upload")
    public String uploadJpgImageFileToAWS(
            @RequestPart(value = "file") MultipartFile multipartFile) throws IOException {
        service.compressAndUpload(new ImageMultipart(multipartFile));
        return "done";
    }
}
