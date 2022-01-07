package com.spring.ws;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("chat")
	public String home() {
		return "chat";
	}

}