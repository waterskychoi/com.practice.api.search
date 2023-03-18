package com.practice.api.search.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	SearchController(){
		
	}
	
	@GetMapping("/test")
	public String test() {
		return "JUST TEST";
	}

}
