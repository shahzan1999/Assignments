package monkhub.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import monkhub.dto.HCfAndLCM;
import monkhub.service.Task1Service;

@RestController
public class ControllerTask1 {

	@Autowired
	Task1Service t1Service;
	
	//get number from user
	@GetMapping("/numbers/{number1}/{number2}")
	public HCfAndLCM getNumber(@PathVariable Long number1, @PathVariable Long number2) {
		HCfAndLCM hl = new HCfAndLCM();
		
		
		hl.setHCF(t1Service.findHCF(number1, number2));
		hl.setLCM(t1Service.findLCM(number1, number2)); 
		
		return hl;
	}
	
	//get a string and convert to Uppercase
		@GetMapping("/strings/uppercase/{string}")
		public String getStringToUpperCase(@PathVariable String string) {
			return t1Service.uppercase(string);
		}
	
	//get a string and return its reverse
	    @GetMapping("/strings/reverse/{string}")
	    public String getStringToReverse(@PathVariable String string) {
		return t1Service.reverse(string);
	}
	
	//get a string and return its vowels
		@GetMapping("/strings/vowels/{string}")
		public String getStringToFindVowels(@PathVariable String string) {
			return t1Service.findVowel(string);
		}
	
	//get a string and remove its whitespace
		@GetMapping("/strings/trimspaces/{string}")
	    public String getStringToRemoveWhiteSpaces(@PathVariable String string) {
			return t1Service.removeSpaces(string);
		}		
	
	// upload a file to local directory	
		@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
			File  convertFile = new File(
					"C:/work/" + file.getOriginalFilename());
			convertFile.createNewFile();
			
			try(FileOutputStream fout = new FileOutputStream(convertFile)) {
				fout.write(file.getBytes());
			} catch( Exception exe) {
				exe.printStackTrace();
			}
			
			return "Uploaded Succesfully";
		}
	
	// appending a file
		@PostMapping("/appendfile/{string}") 
		public String appendingFile(@PathVariable String string) throws IOException {
			FileOutputStream os = new FileOutputStream("C:/work/MyFile.txt",true);
			os.write(string.getBytes(),0,string.length());
			os.close();
			return "appended Successfully";
		}
		
	// upload multiple files to local directory	
		@PostMapping(value="/uploadmultiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public String fileUploadMultiple(@RequestParam("files") List<MultipartFile> files) throws IOException {
			
			int cnt=0;
			for(MultipartFile file: files) {
				File  convertFile = new File(
						"C:/work/" + file.getOriginalFilename());
				convertFile.createNewFile();
				
				try(FileOutputStream fout = new FileOutputStream(convertFile)) {
					fout.write(file.getBytes());
				} catch( Exception exe) {
					exe.printStackTrace();
				}
				cnt++;
			}
			return cnt+ " files uploaded succesfully";
		}
		
	// input data into list and iterate it
		@PostMapping(value="/uploadtoread" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public String uploadToRead(@RequestParam("file") MultipartFile file) throws IOException {
			
			File  convertFile = new File(
					"C:/work/" + file.getOriginalFilename());
			convertFile.createNewFile();
			
			try(FileOutputStream fout = new FileOutputStream(convertFile)) {
				fout.write(file.getBytes());
			} catch( Exception exe) {
				exe.printStackTrace();
			}
			
			// this code is used to take afile and extract it word by word
			Scanner s = new Scanner(new File ("C:/work/" + file.getOriginalFilename()));
			ArrayList<String> list = new ArrayList<String>();
			while(s.hasNext()) {
				list.add(s.next());
			}
			s.close();
			for(String str: list) {
				System.out.println(str);
			}
			
			return "ok";
		}
		
}
