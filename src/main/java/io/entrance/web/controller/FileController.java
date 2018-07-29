package io.entrance.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@EnableAutoConfiguration
public class FileController {
	
	
	
	@RequestMapping(value="/image", method = RequestMethod.POST)
	private String upload_img(@RequestParam("image") MultipartFile img, HttpServletRequest request) throws IOException {
		String phyPath = request.getSession().getServletContext().getRealPath("/");
		String imgPath = phyPath+"/img/";
		byte[] bytes = img.getBytes();
		Path path = Paths.get(imgPath + img.getOriginalFilename());
        Files.write(path, bytes);
        return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/pdf", method = RequestMethod.POST)
	private String upload_pdf(@RequestParam("pdf") MultipartFile pdf, HttpServletRequest request) throws IOException {
		String phyPath = request.getSession().getServletContext().getRealPath("/");
		String imgPath = phyPath+"/pdf/";
		byte[] bytes = pdf.getBytes();
		Path path = Paths.get(imgPath + pdf.getOriginalFilename());
        Files.write(path, bytes);
        return "redirect:/dashboard";
	}
	
}