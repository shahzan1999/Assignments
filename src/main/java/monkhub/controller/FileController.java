package monkhub.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import monkhub.model.FileInfo;
import monkhub.service.FileService;


@RestController()
public class FileController {

	@Autowired
	FileService fileService;
	
	@PostMapping(value="/v2/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file)  {
		try {
			fileService.save(file);
			return "uploaded";
		} catch (Exception e) {
			return "not uploaded";
		}
	}
	
	@GetMapping("/v2/files")
	public List<FileInfo> getListFiles() {
		List<FileInfo> fileInfos = fileService.loadAll().map(path -> {
			String fileName = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
			  .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
			return new FileInfo(fileName,url);
		}).collect(Collectors.toList());
		return fileInfos;
	}
	
	@GetMapping("/v2/files/{filename:.+}") 
	public Resource getFile(@PathVariable String filename) {
		return fileService.load(filename);
	}
	
}
