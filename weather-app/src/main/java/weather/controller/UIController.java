package weather.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import weather.service.DarkSkyAPIService;
import weather.util.DateUtil;

@Controller
public class UIController {
	private static final Logger logger = LogManager.getLogger("WeatherAppLogger");
	
	@Autowired
	DarkSkyAPIService darkSkyAPIService;

	@GetMapping("/weatherNow")
	public String showWeather(Model model) {		
		DateUtil dateUtil = new DateUtil();
		logger.info("/wetherNow URL triggered");
		model.addAttribute("cityWeatherList",darkSkyAPIService.getTodayWeather());
		model.addAttribute("localDateToday",dateUtil.getLocalDateToday());
		
		return "weather";				
	}

}