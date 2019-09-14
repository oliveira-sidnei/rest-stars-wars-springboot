package br.com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String helloWrld() {
		return "Hello World!";
	}

}
