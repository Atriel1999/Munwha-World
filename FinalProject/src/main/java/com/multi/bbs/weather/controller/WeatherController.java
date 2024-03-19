package com.multi.bbs.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {
	
	@GetMapping("/weather")
	public String Weather() {
		return "weather/weather";
	}

}
