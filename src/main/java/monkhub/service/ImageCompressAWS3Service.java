package monkhub.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import monkhub.file.ImageMultipart;
import monkhub.file.ImageSource;
import monkhub.file.JpgImage;
import monkhub.file.MultipartImage;



@Service
public class ImageCompressAWS3Service {

//	@Autowired
//	JpgImage jp;
	
	@Autowired
	AWSS3ServiceImpl as;
	
	@Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;
	
	public void compressAndUpload(ImageSource imageMultipart) throws IOException {
		
		 BufferedImage sourceImage = ImageIO.read(imageMultipart.asFile());

		 BufferedImage resizedImage = Scalr.resize(sourceImage, Scalr.Mode.FIT_EXACT, 200, 300);
		
      //  BufferedImage resizedImage = jp.resize(imageMultipart.asFile());
        
		// String fname = imageMultipart.asFile().getName();
		 File afile = new File("saved.jpg");
		 ImageIO.write(resizedImage, "jpg", afile);
		 System.out.println("lol "+afile.getName());
         uploadFileToS3Bucket(bucketName, afile);
         
//         FileOutputStream os = new FileOutputStream("image");
//         byte[] b = ((MultipartFile) afile).getBytes();
//         DiskFileItem fileItem = new DiskFileItem("file", JpgImage.EXTENSION, false, afile.getName(), (int) afile.length() , afile.getParentFile());
//         fileItem.getOutputStream();
//         MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
         
         
		 
		// BufferedImage originalImage = ImageIO.read(new File("path to file"));
//		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		 ImageIO.write( resizedImage, "jpg", baos );
//		 baos.flush();

//		 //MultipartFile multipartFile = new MultipartImage(baos.toByteArray());
//		 FileInputStream is = new FileInputStream(afile);
//		 MultipartFile multipartFile = new MockMultipartFile(afile.getName(),afile.getName(),"jpg",is);
	}
	 
	
	 private void uploadFileToS3Bucket(final String bucketName, final File file) {
	        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
	        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
	        System.out.println("lol "+file.getName());
	        amazonS3.putObject(putObjectRequest);
	        System.out.println("lol "+file.getName());
	    }


	public void compressAndUploadf(MultipartFile multipartFile) {
	   File file = convertMultiPartFileToFile(multipartFile);
       uploadFileToS3Bucket(bucketName, file);

	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
        	throw new RuntimeException("errorror: "+ ex);
        }
        return file;
    }
	
}
