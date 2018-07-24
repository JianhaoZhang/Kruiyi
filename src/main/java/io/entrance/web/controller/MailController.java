package io.entrance.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@EnableAutoConfiguration
public class MailController {
	
	@Autowired
	private JavaMailSender agent;
	
	
	private void sendtomerchant(String name,String email,String company,String position,String sex, String address, String tel,
			String zip, String website, String mobile, String fax, String matter, String body) throws Exception{
		MimeMessage message = agent.createMimeMessage();
		message.setHeader("Content-Type", "text/plain; charset=UTF-8");
		MimeMessageHelper help = new MimeMessageHelper(message,true);
		String stemp = "";
		if (sex.equals("male")) {
			stemp = "先生";
		}else if (sex.equals("femail")) {
			stemp = "女士";
		}
		
		help.setTo("jonah@kruiyi.com");
		help.setText("<p>"+company+" 客户意见反馈: </p>"+
				"<p>来自 "+ name + " "+stemp+ " ("+ position +")"+"</p>"+
				"<p>内容: "+matter+"</p>"+
				"<p>详情: "+body+"</p>"+
				"<p>联系方式:</p>" +
				"<p>邮箱: "+ email +"</p>" +
				"<p>地址: "+ address +"</p>" +
				"<p>邮编: "+ zip +"</p>" +
				"<p>电话: "+ tel +"</p>" +
				"<p>手机: "+ mobile +"</p>" +
				"<p>传真: "+ fax +"</p>" +
				"<p>网站: "+ website +"</p>"
				,true);
		help.setSubject("DO NOT REPLY - 来自"+company+"的客户建议: ");
		agent.send(message);


	}
	
	
	@RequestMapping(value= "getadvice", method = RequestMethod.POST)
	public String order(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("company") String company,
			@RequestParam("position") String position, @RequestParam("sex") String sex, @RequestParam("address") String address,
			@RequestParam("tel") String tel, @RequestParam("zip") String zip, @RequestParam("website") String website,
			@RequestParam("mobile") String mobile, @RequestParam("fax") String fax, @RequestParam("matter") String matter,
			@RequestParam("body") String body,
			Model model,HttpServletRequest request) throws Exception {
		System.out.println("advicing");
		
				sendtomerchant(name,email,company,position,sex,address,tel,zip,website,mobile,fax,matter,body);
		
		
		model.addAttribute("RESULT", "Successfully created order");
		return "redirect:/";
		
	}


}
