package weather.controller;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import weather.service.DarkSkyAPIService;

@Controller
public class UIController {
	
	@Autowired
	DarkSkyAPIService darkSkyAPIService;

	@GetMapping("/weatherNow")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "weather";
	}	

	@GetMapping("/weatherNow2")
	public String showWeather(Model model) {		
		model.addAttribute("cityWeatherList",darkSkyAPIService.getTodayWeather());
		return "weather";				
	}

}