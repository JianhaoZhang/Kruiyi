package io.entrance.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import io.entrance.web.model.News;
import io.entrance.web.model.NewsRepo;
import io.entrance.web.model.Products;
import io.entrance.web.model.ProductsRepo;

@Controller
@EnableAutoConfiguration
public class ContentController {
	
	@Autowired
	private NewsRepo newsrepo;
	
	@Autowired
	private ProductsRepo productsrepo;
	
	@RequestMapping(value="/news", method = RequestMethod.POST)
	private String newsimport(@RequestParam("id") long id,@ModelAttribute("newsform") News news, Model model) {
		System.out.println("posting news");
		
		if (id==0) {
			News instance = new News(news.getTitle(),news.getDate(),news.getBody());
			this.newsrepo.save(instance);
		}else {
			News instance = newsrepo.findById(id).get(0);
			instance.setBody(news.getBody());
			instance.setDate(news.getDate());
			instance.setTitle(news.getTitle());
			this.newsrepo.save(instance);
		}
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/products", method = RequestMethod.POST)
	private String newsimport(@RequestParam("pid") long pid, @ModelAttribute("productsform") Products products, Model model) {
		System.out.println("posting news");
		
		if (pid==0) {
			Products instance = new Products(products.getName(),products.getBrand(),products.getType(),products.getDescription(),products.getPath());
			this.productsrepo.save(instance);
		}else {
			Products instance = productsrepo.findByPid(pid).get(0);
			instance.setName(products.getName());
			instance.setBrand(products.getBrand());
			instance.setDescription(products.getDescription());
			instance.setType(products.getType());
			instance.setPath(products.getPath());
			this.productsrepo.save(instance);
		}
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/render")
	private String render(HttpServletRequest request) throws IOException {
		String phyPath = request.getSession().getServletContext().getRealPath("/");
		//System.out.println(phyPath);
		ArrayList<News> newsarray = (ArrayList<News>) newsrepo.findAll();
		ArrayList<Products> productsarray = (ArrayList<Products>) productsrepo.findAll();
		
		create_articles(newsarray,phyPath);
		create_news_menu(newsarray,phyPath);
		initialize_news(phyPath);
		
		create_products(productsarray,phyPath);
		create_products_menu(productsarray,phyPath);
		initialize_products(productsarray,phyPath);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/purge")
	private String purge(HttpServletRequest request) {
		String phyPath = request.getSession().getServletContext().getRealPath("/");
		try {
			FileUtils.cleanDirectory(new File(phyPath+"/newsmenu"));
			FileUtils.cleanDirectory(new File(phyPath+"/news"));
			FileUtils.cleanDirectory(new File(phyPath+"/productsmenu"));
			FileUtils.cleanDirectory(new File(phyPath+"/products"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("purge failed");
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	//initialize news page
	private void initialize_news(String path)throws IOException {
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
	
	//initialize products page
	private void initialize_products(ArrayList<Products> productsarray,String path)throws IOException {
		ArrayList<String> brandlist = analyzebrands(productsarray);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/menu/products.html"));
		writer.write("<!-- Main -->\r\n" + 
				"			<div id=\"main\" class=\"container\">\r\n" + 
				"				<div class=\"row\">\r\n" + 
				"					<div id=\"side\" class=\"2u\">\r\n");
		String side = readFile(path+"/productsmenu/brandmenu.html",StandardCharsets.UTF_8);
		writer.write(side+"\r\n");
		writer.write("</div>\r\n" + 
				"<div id=\"side2\" class=\"2u\">\r\n");
		String side2 = readFile(path+"/productsmenu/"+brandlist.get(0)+".html",StandardCharsets.UTF_8);
		writer.write(side2+"\r\n");
		writer.write("</div>\r\n" + 
				"				\r\n" + 
				"					<div class=\"6u skel-cell-important\">\r\n" + 
				"						<section id=\"brandcontent\"><header>\r\n");
		ArrayList<Products> itembybrand = (ArrayList<Products>)productsrepo.findByBrand(brandlist.get(0));
		String content = readFile(path+"/products/products"+itembybrand.get(0).getPid()+".html",StandardCharsets.UTF_8);
		writer.write(content+"\r\n");
		writer.write("</section>\r\n" + 
				"					</div>\r\n" + 
				"					\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			<!-- Main -->");
		writer.close();
		
	}
	
	//render all news by id
	private void create_articles(ArrayList<News> newsarray,String path) throws IOException {
		
		for (int i=0; i<newsarray.size();i++) {
		News temp = newsarray.get(i);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/news/news"+temp.getId()+".html"));
		writer.write("<header>\r\n<h2>"+temp.getTitle()+"</h2>\r\n<span class=\"byline\">"+temp.getDate()+"</span>\r\n</header>\r\n");
		writer.write(temp.getBody());
		writer.close();
		}
		
	}
	
	//render all products by pid
	private void create_products(ArrayList<Products> productsarray,String path) throws IOException {
		
		for (int i=0; i<productsarray.size();i++) {
		Products temp = productsarray.get(i);
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/products/products"+temp.getPid()+".html"));
		writer.write("<header>\r\n<h2>"+temp.getName()+"</h2>\r\n<span class=\"byline\">"+temp.getBrand()+"</span>\r\n</header>\r\n");
		writer.write(temp.getDescription());
		writer.close();
		}
		
	}
	
	//render news section resources
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
	
	//render products section resources
	private void create_products_menu(ArrayList<Products> productsarray,String path) throws IOException{
		
		double number = productsarray.size();
		ArrayList<String> brandlist = analyzebrands(productsarray);
		
		create_brand_sidebar(brandlist,path);
		create_brand_menu(brandlist,path);
		
	}
	
	//create sidebar by brand
	private void create_brand_sidebar(ArrayList<String> brandlist,String path) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/productsmenu/brandmenu.html"));
		writer.write("<section class=\"sidebar\">\r\n" + 
				"							<header>\r\n" + 
				"								<h2>Brand Menu</h2>\r\n" + 
				"							</header>\r\n" + 
				"							<ul class=\"style1\">\r\n");
		for (int i=0;i<brandlist.size();i++) {
			writer.write("<li><a href=\"#\" onclick=\"loadDoc('productsmenu/"+brandlist.get(i)+"','side2')\">"+brandlist.get(i)+"</a></li>\r\n");
		}
		
		writer.write("</section>");
		
		writer.close();
	}
	
	private void create_brand_menu(ArrayList<String> brandlist, String path) throws IOException {
		for (String brand : brandlist) {
			ArrayList<Products> productsbrandarray = (ArrayList<Products>)productsrepo.findByBrand(brand);
			BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/productsmenu/"+brand+".html"));
			writer.write("<section class=\"sidebar\">\r\n" + 
					"							<header>\r\n" + 
					"								<h2>Item Menu</h2>\r\n" + 
					"							</header>\r\n" + 
					"							<ul class=\"style1\">\r\n");
			for (Products p : productsbrandarray) {
				writer.write("<li><a href=\"#\" onclick=\"loadDoc('products/products"+p.getPid()+"','brandcontent')\">"+p.getName()+"</a></li>\r\n");
			}
			
			writer.write("</section>\r\n");
			writer.close();
		}
	}
	
	//get brand list without duplication
	private ArrayList<String> analyzebrands(ArrayList<Products>productsarray){
		
		ArrayList<String> al = new ArrayList<>();
		
		for (Products p : productsarray) {
			al.add(p.getBrand());
		}
		
		LinkedHashSet<String> hs = new LinkedHashSet<>();
		hs.addAll(al);
		al.clear();
		al.addAll(hs);
		
		System.out.println("Brand array: "+al.toString());
		return al;
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
		}
	

}
