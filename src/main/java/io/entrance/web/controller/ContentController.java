package io.entrance.web.controller;

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
	
	

}
