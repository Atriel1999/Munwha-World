package com.multi.bbs.museum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MuseumController {
	
	@GetMapping("/museumsearch")
	public String museumSearch() {
		return "museum/museumsearch";
	}
	
	@GetMapping("/museumview")
	public String museumView() {
		return "museum/museumview";
	}

}
