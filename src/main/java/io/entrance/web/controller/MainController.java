package io.entrance.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.entrance.web.model.MessageRepo;




@Controller
@EnableAutoConfiguration
public class MainController {
	
	
	@Autowired
	private MessageRepo repo;
	
	@RequestMapping("/")
	public String showindex(Model model) {
		return "index";
	}
	
	
	

}
