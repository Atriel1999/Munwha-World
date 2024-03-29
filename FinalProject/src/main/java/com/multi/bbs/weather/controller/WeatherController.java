package com.multi.bbs.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {
	
	@GetMapping("/weather")
	public String Weather() {
		return "weather/weather";
	}

	@GetMapping("/weatherAll")
	public String WeatherAll() {
		return "weather/weather-all";
	}
	
}
