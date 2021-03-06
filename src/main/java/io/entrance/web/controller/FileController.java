package io.entrance.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		new File(phyPath+"/img").mkdirs();
		Path path = Paths.get(imgPath + img.getOriginalFilename());
        Files.write(path, bytes);
        return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/pdf", method = RequestMethod.POST)
	private String upload_pdf(@RequestParam("pdf") MultipartFile pdf, HttpServletRequest request) throws IOException {
		if (request.getCharacterEncoding()!=null) {
			System.out.println(request.getCharacterEncoding());
		}else {
			System.out.println("null");
		}
	    String phyPath = request.getSession().getServletContext().getRealPath("/");
		String pdfPath = phyPath+"/pdf/";
		byte[] bytes = pdf.getBytes();
		new File(phyPath+"/pdf").mkdirs();
		String filename = pdf.getOriginalFilename();
		String realname = new StringEscapeUtils().unescapeHtml4(filename);
		System.out.println(realname);
		Path path = Paths.get(pdfPath + realname);
		synchronized(realname) {
			if (realname!=null) {
				Files.write(path, bytes);
			}
		}
        return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/pdf/{file_name}", method = RequestMethod.GET)
	public void getFile(
	    @PathVariable("file_name") String fileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String phyPath = request.getSession().getServletContext().getRealPath("/");
	    File initialFile = new File(phyPath+"pdf/"+fileName+".pdf");
	    System.out.println(phyPath+"pdf/\""+fileName+"\".pdf");
	    InputStream is = new FileInputStream(initialFile);
	    org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	    response.flushBuffer();
	    response.setContentType("application/pdf");
	    

	}
	
	@RequestMapping(value = "/.well-known/pki-validation/D442EBF1CB0F460A09718EB0FA163306.txt", method = RequestMethod.GET)
	public void getValid( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String phyPath = request.getSession().getServletContext().getRealPath("/");
	    File initialFile = new File(phyPath+"menu/"+"D442EBF1CB0F460A09718EB0FA163306.txt");
	    InputStream is = new FileInputStream(initialFile);
	    org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	    response.flushBuffer();
	    response.setContentType("application/txt");
	    
	}
	
}