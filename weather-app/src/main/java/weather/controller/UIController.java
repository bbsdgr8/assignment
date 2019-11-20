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
import weather.util.DateUtil;

@Controller
public class UIController {
	
	@Autowired
	DarkSkyAPIService darkSkyAPIService;

	@GetMapping("/weatherNow")
	public String showWeather(Model model) {		
		DateUtil dateUtil = new DateUtil();
		model.addAttribute("cityWeatherList",darkSkyAPIService.getTodayWeather());
		model.addAttribute("localDateToday",dateUtil.getLocalDateToday());
		
		return "weather";				
	}

}