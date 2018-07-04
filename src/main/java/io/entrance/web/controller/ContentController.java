package io.entrance.web.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.entrance.web.model.News;
import io.entrance.web.model.NewsRepo;

@Controller
@EnableAutoConfiguration
public class ContentController {
	
	@Autowired
	private NewsRepo newsrepo;
	
	@RequestMapping(value="/news", method = RequestMethod.POST)
	private String newsimport(@ModelAttribute("newsform") News news, Model model) {
		System.out.println("posting news");
		
		if (news.getId()==0) {
			News instance = new News(news.getTitle(),news.getDate(),news.getBody());
			this.newsrepo.save(instance);
		}else {
			this.newsrepo.save(news);
		}
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/render")
	private String render(HttpServletRequest request) throws IOException {
		String phyPath = request.getSession().getServletContext().getRealPath("/");
		//System.out.println(phyPath);
		ArrayList<News> newsarray = (ArrayList<News>) newsrepo.findAll();
		create_articles(newsarray,phyPath);
		create_news_menu(newsarray,phyPath);
		initialize(newsarray,phyPath);
		
		return "redirect:/";
	}
	
	private void initialize(ArrayList<News> newsarray,String path)throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/menu/news.html"));
		writer.write("<!-- Main -->\r\n" + 
				"			<div id=\"main\" class=\"container\">\r\n" + 
				"				<div class=\"row\">\r\n" + 
				"					<div id=\"side\" class=\"3u\">\r\n");
		
		String side = readFile(path+"/newsmenu/newsmenu1.html",StandardCharsets.UTF_8);
		writer.write(side+"\r\n");
		writer.write("</div>\r\n" + 
				"				\r\n" + 
				"					<div class=\"9u skel-cell-important\">\r\n" + 
				"						<section id=\"newscontent\">");
		
		String article = readFile(path+"/news/news1.html",StandardCharsets.UTF_8);
		writer.write(article+"\r\n");
		writer.write("</section>\r\n" + 
				"					</div>\r\n" + 
				"					\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			<!-- Main -->");
		
		writer.close();
		
	}
	
	private void create_articles(ArrayList<News> newsarray,String path) throws IOException {
		
		for (int i=0; i<newsarray.size();i++) {
		News temp = newsarray.get(i);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/news/news"+temp.getId()+".html"));
		writer.write("<header>\r\n<h2>"+temp.getTitle()+"</h2>\r\n<span class=\"byline\">"+temp.getDate()+"</span>\r\n</header>\r\n");
		writer.write(temp.getBody());
		writer.close();
		}
		
	}
	
	private void create_news_menu(ArrayList<News> newsarray,String path) throws IOException {
		
		double number = newsarray.size();
		int counter = newsarray.size();
		int pages = (int) Math.ceil(number/10.0);
		
		for (int i=0; i<pages;i++) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/newsmenu/newsmenu"+(i+1)+".html"));
			writer.write("<section class=\"sidebar\">\r\n" + 
					"							<header>\r\n" + 
					"								<h2>News Menu</h2>\r\n" + 
					"							</header>\r\n" + 
					"							<ul class=\"style1\">\r\n");
			
			for(int j=0;j<10;j++) {
				if (counter>0) {
					writer.write("<li><a href=\"#\" onclick=\"loadDoc('news/news"+counter+"','newscontent')\">"
							+newsarray.get(counter-1).getTitle()+"</a></li>\r\n");
					counter-=1;
				}else {
					break;
				}
				
			}
			
			writer.write("<h2>Pages:</h2>\r\n");
			
			for(int k=0;k<pages;k++) {
				writer.write("<button onclick=\"loadDoc('newsmenu/newsmenu"+(k+1)+"','side')\">"+(k+1)+"</button>\r\n");
			}
			
			writer.write("</section>");
			
			writer.close();
		}
	}
	
	static String readFile(String path, Charset encoding) throws IOException{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
		}
	

}
