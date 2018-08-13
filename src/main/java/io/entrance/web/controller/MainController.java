package io.entrance.web.controller;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.entrance.web.model.NewsRepo;
import io.entrance.web.model.SaltRepo;
import io.entrance.web.model.Account;
import io.entrance.web.model.AccountRepo;
import io.entrance.web.model.Salt;




@Controller
@EnableAutoConfiguration
public class MainController {
	
	
	@Autowired
	private SaltRepo saltrepo;
	
	@Autowired
	private AccountRepo accountrepo;
	
	@RequestMapping("/")
	public String showindex(Model model) {
		return "index.html";
	}
	
	@RequestMapping("/create")
	public String create(Model model) {
		return "create-account.html";
	}
	
	//login page mapping
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request,HttpSession session) {
		Cookie[] cookies = request.getCookies();
		if (session.getAttribute("user") != null) {
			
					return "redirect:/dashboard";
					
		}
		return "login-account.html";
	}
		
	//allow entering dashboard if active cookies is alive
	@RequestMapping("/dashboard")
	public String dashboard(Model model,HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length>1) {
			String encoded = "";
			String name = "";
			for (int i=0;i<cookies.length;i++) {
			if (cookies[i].getName().equals("user")) {
				encoded = cookies[i].getValue();
				}
			
			if (cookies[i].getName().equals("info")) {
				name = cookies[i].getValue();					}
			}
			String salted = "";
			if (saltrepo.findByName(name)!=null) {
				salted = name+(saltrepo.findByName(name).getTimestamp());
			}
			if (Base64.getEncoder().encodeToString(salted.getBytes("utf-8")).equals(encoded)) {
				return "dashboard.jsp";
			}
			model.addAttribute("ERR", "Not logged in yet!");
			return "redirect:/";
		}else {
			model.addAttribute("ERR", "Not logged in yet!");
			return "redirect:/";
		}
	}
	
	//log out by deleting cookies and invalidate session
	@RequestMapping("/logout")
	public String logout(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			if (cookies.length>1) {
			for (int i=0;i<cookies.length;i++) {
				if (cookies[i].getName().equals("user")||cookies[i].getName().equals("info")) {
				Cookie nullcookie = new Cookie("user","");
				Cookie nullcookie2 = new Cookie("info","");
				nullcookie.setMaxAge(0);
				response.addCookie(nullcookie);
				nullcookie2.setMaxAge(0);
				response.addCookie(nullcookie2);
				}
			}
			session.invalidate();
			return "redirect:/";
			}
			model.addAttribute("ERR", "Not logged in yet!");
			return "redirect:/";
		}else {
			model.addAttribute("ERR", "Not logged in yet!");
			return "redirect:/";
		}
	}
	
	
	//post create account
	@RequestMapping(value= "repo", method = RequestMethod.POST)
	public String create(@ModelAttribute("accountform") Account acc,Model model) throws UnsupportedEncodingException {
		System.out.println("creating");
		
		if (accountrepo.findByName(acc.getName())!= null) {
			model.addAttribute("ERR2", "Account already exists!");
		}else {
			
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Salt salt= new Salt(acc.getName(), timestamp);
			this.saltrepo.save(salt);
			String saltedpassword = acc.getPassword()+timestamp;
			String encodedpassword = Base64.getEncoder().encodeToString(saltedpassword.getBytes("utf-8"));
			acc.setPassword(encodedpassword);
			this.accountrepo.save(acc);
		}
		return "redirect:/";
	}
	
	//login and initialize cookies
	@RequestMapping(value= "log", method = RequestMethod.POST)
	public String quest(@RequestParam("name") String name, @RequestParam("password") String password,Model model,HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("logging");
		if (accountrepo.findByName(name)!= null) {
			String salt = "";
			if (saltrepo.findByName(name)!= null) {
				salt = saltrepo.findByName(name).getTimestamp();
			}
			String answer = Base64.getEncoder().encodeToString((password+salt).getBytes("utf-8"));
			if (accountrepo.findByName(name).getPassword().equals(answer)) {
				model.addAttribute("MSG", name);
				String encodedname = Base64.getEncoder().encodeToString((name+salt).getBytes());
				Cookie active = new Cookie("user",encodedname);
				Cookie info = new Cookie("info",name);
				active.setMaxAge(10000);
				info.setMaxAge(10000);
				response.addCookie(active);
				response.addCookie(info);
				//session.setAttribute("user", name);
				return "redirect:/dashboard";
			}else {
				model.addAttribute("MSG", "Wrong Password!");
				return "redirect:/login";
			}
				
		}else {
			model.addAttribute("MSG", "Account does not exist!");
			return "redirect:/login";
		}
	}
	
	

}
