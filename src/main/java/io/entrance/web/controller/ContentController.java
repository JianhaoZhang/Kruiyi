package io.entrance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import io.entrance.web.model.NewsRepo;

@Controller
@EnableAutoConfiguration
public class ContentController {
	
	@Autowired
	private NewsRepo newsrepo;
	

}
